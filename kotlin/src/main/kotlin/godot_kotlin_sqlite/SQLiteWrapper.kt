package godot_kotlin_sqlite

import cnames.structs.sqlite3_stmt
import godot.File
import godot.JSON
import godot.ProjectSettings
import godot.Reference
import godot.core.*
import kotlinx.cinterop.*
import org.godotengine.kotlin.annotation.RegisterClass
import org.godotengine.kotlin.annotation.RegisterFunction
import org.godotengine.kotlin.annotation.RegisterProperty
import sqlite3.*

fun callback(closure: COpaquePointer?, argc: Int, argv: CPointer<CPointerVar<ByteVar>>?, azColName: CPointer<CPointerVar<ByteVar>>?): Int {
    val columnDictionary = Dictionary()
    /* Get a reference to the instanced object */
    val stableRef = closure?.asStableRef<SQLiteWrapper>()
    val obj = stableRef?.get()
    val stmt: CPointer<sqlite3_stmt>? = sqlite3_next_stmt(obj?.db, null)
    var columnValue: Variant

    /* Loop over all columns and add them to the Dictionary */
    for (i in 0 until argc) {
        /* Check the column type and do correct casting */
        columnValue = when (sqlite3_column_type(stmt, i)) {
            SQLITE_INTEGER -> {
                Variant(sqlite3_column_int64(stmt, i).toInt())
            }
            SQLITE_FLOAT -> {
                Variant(sqlite3_column_double(stmt, i))
            }
            SQLITE_TEXT -> {
                sqlite3_column_text (stmt, i)?.reinterpret<ByteVar>()?.toKString()?.let { Variant(it) }!!
            }
            else -> {
                sqlite3_column_text (stmt, i)?.reinterpret<ByteVar>()?.toKString()?.let { Variant(it) }!!
            }
        }
        columnDictionary[azColName?.get(i)?.toKString()?.let { Variant(it) }!!] = columnValue
    }
    /* Add result to query_result Array */
    obj?.queryResult?.append(Variant(columnDictionary))

    /* cleanup! */
    stableRef?.dispose()

    return 0
}

data class TableData(
        var name: String = "",
        var sql: String = "",
        var rowArray: GDArray = GDArray()
)

@RegisterClass("addons/godot-kotlin-sqlite/bin")
class SQLiteWrapper : Reference() {

    var db : CPointer<sqlite3>? = null
    @RegisterProperty(false, "\"default\"")
    var path : String = "default"
    @RegisterProperty(false, "\"\"")
    var errorMessage : String = ""
    @RegisterProperty(false, "false")
    var verboseMode : Boolean = false
    @RegisterProperty(false, "false")
    var foreignKeys : Boolean = false
    @RegisterProperty(false, "godot.core.GDArray()")
    var queryResult : GDArray = GDArray()

    @RegisterFunction
    fun openDatabase() : Boolean {
        var rc = -1
        /* Add .db to the path String if not present */
        val ending = ".db"
        if (path != ":memory:" && !path.endsWith(ending))
        {
            path += ending
        }
        /* Find the real path */
        path = ProjectSettings.globalizePath(path.trim())

        /* Try to open the database */
        /* CValuesRef needs to be created for this purpose... */
        memScoped {
            val q: CPointerVar<sqlite3> = alloc()
            rc = sqlite3_open(path, q.ptr)
            db = q.value
        }

        if (rc != SQLITE_OK)
        {
            GD.print("GDSQLite Error: Can't open database: " + sqlite3_errmsg(db)?.toKString()!!)
            db = null
            return false
        }
        else {
            GD.print("Opened database successfully ($path)")
        }

        /* Try to enable foreign keys. */
        if (foreignKeys)
        {
            var zErrMsg : CPointer<ByteVar>? = null
            memScoped {
                val q: CPointerVar<ByteVar> = alloc()
                rc = sqlite3_exec(db, "PRAGMA foreign_keys=on;", null, null, q.ptr)
                zErrMsg = q.value
            }
            if (rc != SQLITE_OK)
            {
                GD.print("GDSQLite Error: Can't enable foreign keys: " + zErrMsg?.toKString().toString())
                sqlite3_free(zErrMsg)
                return false
            }
        }

        return true
    }

    @RegisterFunction
    fun importFromJSON(importPath : String) : Boolean
    {
        println("number 1")
        /* Add .json to the import_path String if not present */
        val ending = ".json"
        var extImportPath = importPath
        if (!extImportPath.endsWith(ending))
        {
            extImportPath += ending
        }
        /* Find the real path */
        extImportPath = ProjectSettings.globalizePath(extImportPath.trim())
        println("number 2")
        /* CharString object goes out-of-scope when not assigned explicitly */
        /* NOT REQUIRED IN KOTLIN */

        /* Open the json-file and parse its contents using the Godot JSON singleton */
        val file = File()
        println(extImportPath)
        if (file.open(extImportPath, File.READ) != GodotError.OK)
        {
            GD.print("GDSQLite Error: Failed to open specified json-file ($importPath)")
            return false
        }
        println("number 25")
        val jsonString : String = file.getAsText()
        println("number 275")
        file.close()
        println("number 3")
        /* Attempt to open the json and, if unsuccessful, throw a parse error specifying the erroneous line */
        val result = JSON.parse(jsonString)
        if (result.getError() != GodotError.OK)
        {
            /* Throw a parsing error */
            GD.print("GDSQLite Error: parsing failed! reason: " + result.getErrorString() + ", at line: " + result.getErrorLine())
            return false
        }
        println("number 4")
        val importJSON : GDArray = result.getResult().toGDArray()

        /* Check if the database is open and, if not, attempt to open it */
        if (db == null)
        {
            /* Open the database using the open_db method declared above */
            openDatabase()
        }

        println("number 5")
        /* Find all tables that are present in this database */
        query("SELECT name FROM sqlite_master WHERE type = 'table';")
        val oldTableNames = GDArray()
        val oldNumberOfTables : Int = queryResult.size()
        for (i in 0 until oldNumberOfTables)
        {
            val tableDictionary : Dictionary = queryResult[i]?.toDictionary()!!
            oldTableNames.append(tableDictionary["name"]!!)
        }

        val tablesToImport = mutableListOf<TableData>()
        /* Validate the json structure and populate the tables_to_import vector */
        if (!validateJSON(importJSON, tablesToImport))
        {
            return false
        }
        println("number 6")
        /* Drop all old tables present in the database */
        for (i in 0 until oldNumberOfTables)
        {
            dropTable(oldTableNames[i].toString())
        }
        println("number 7")
        /* Add all new tables and fill them up with all the rows */
        for (table in tablesToImport)
        {
            query(table.sql)
            insertRows(table.name, table.rowArray)
        }
        return true
    }

    @RegisterFunction
    fun exportToJSON(exportPath : String) : Boolean
    {
        /* Get all names and sql templates for all tables present in the database */
        query("SELECT name,sql FROM sqlite_master WHERE type = 'table';")
        val numberOfTables : Int = queryResult.size()
        val databaseArray = GDArray()
        for (i in 0 until numberOfTables)
        {
            /* Deep copy of the Dictionary is required as Dictionaries are passed with reference */
            /* Without doing this, future queries would overwrite the table information */
            databaseArray.append(Variant(queryResult[i]?.toDictionary()?.duplicate(true)!!))
        }

        /* Add .json to the import_path String if not present */
        val ending = ".json"
        var extExportPath = exportPath
        if (!extExportPath.endsWith(ending))
        {
            extExportPath += ending
        }
        /* Find the real path */
        extExportPath = ProjectSettings.globalizePath(extExportPath.trim())

        val file = File()
        if (file.open(extExportPath, File.WRITE) != GodotError.OK)
        {
            GD.print("GDSQLite Error: Can't open specified json-file, file does not exist or is locked")
            return false
        }

        /* Construct a Dictionary for each table, convert it to JSON and write it to file */
        var fileBuffer = "["
        for (i in 0 until numberOfTables)
        {
            val tableDictionary : Dictionary = databaseArray[i]?.toDictionary()!!
            var jsonString : String
            var queryString : String

            queryString = "SELECT * FROM " + tableDictionary["name"].toString() + ";"
            query(queryString)
            tableDictionary["row_array"] = queryResult

            jsonString = JSON.print(Variant(tableDictionary))
            /* CharString object goes out-of-scope when not assigned explicitly */
            fileBuffer += jsonString
            if (i != numberOfTables - 1)
            {
                fileBuffer += ","
            }
        }
        fileBuffer += "]"
        file.storeString(fileBuffer)
        file.close()
        return true
    }

    @RegisterFunction
    fun closeDatabase() {
        if (db != null)
        {
            // Cannot close database!
            if (sqlite3_close_v2(db) != SQLITE_OK)
            {
                GD.print("GDSQLite Error: Cannot close database!")
            }
            else
            {
                db = null
                GD.print("Closed database ($path)")
            }
        }
    }

    @RegisterFunction
    fun query(queryString : String) : Boolean {
        var zErrMsg : CPointer<ByteVar>? = null
        var rc = -1

        GD.print("inside 1")

        if (verboseMode)
        {
            GD.print(queryString)
        }
        /* Clear the previous query results */
        queryResult.clear()

        val stableRef = StableRef.create(this)
        val voidPointer = stableRef.asCPointer()
        /* Execute SQL statement */
        memScoped {
            val q: CPointerVar<ByteVar> = alloc()
            rc = sqlite3_exec(db, queryString, staticCFunction(::callback), voidPointer, q.ptr)
            zErrMsg = q.value
        }
        /* cleanup! */
        stableRef.dispose()

        errorMessage = zErrMsg?.toKString().toString()
        if (rc != SQLITE_OK)
        {
            GD.print(" --> SQL error: $errorMessage")
            sqlite3_free(zErrMsg)
            return false
        }
        else if (verboseMode)
        {
            GD.print(" --> Query succeeded")
        }
        return true
    }

    @RegisterFunction
    fun createTable(tableName : String, tableDictionary : Dictionary) : Boolean {
        var typeString : String
        val integerTypeString = "int"
        /* Create SQL statement */
        var queryString = "CREATE TABLE IF NOT EXISTS $tableName ("

        var columnDictionary : Dictionary
        val columns : GDArray = tableDictionary.keys
        val numberOfColumns : Int = columns.size()
        for (i in 0 until numberOfColumns) {
            columnDictionary = tableDictionary[columns[i]!!]?.toDictionary()!!
            if (!columnDictionary.has("data_type"))
            {
                GD.print("GDSQLite Error: The field 'data_type' is a required part of the table dictionary")
                return false
            }
            queryString += "" + columns[i] + " "
            typeString = columnDictionary["data_type"].toString()
            queryString += if (typeString.toLowerCase().startsWith(integerTypeString)) {
                "INTEGER"
            } else {
                typeString
            }
            /* Primary key check */
            if (columnDictionary["primary_key", false]?.toBoolean()!!)
            {
                queryString += " PRIMARY KEY"
            }
            /* Default check */
            if (columnDictionary.has("default"))
            {
                queryString += " DEFAULT " + columnDictionary["default"]
            }
            /* Autoincrement check */
            if (columnDictionary["auto_increment", false]?.toBoolean()!!)
            {
                queryString += " AUTOINCREMENT"
            }
            /* Not null check */
            if (columnDictionary["not_null", false]?.toBoolean()!!)
            {
                queryString += " NOT NULL"
            }
            /* Apply foreign key constraint. */
            if (foreignKeys)
            {
                if (columnDictionary.has("foreign_key")) {
                    val definition: String = columnDictionary["foreign_key"].toString()
                    val elements: List<String> = definition.split(".")
                    if (elements.size == 2) {
                        val columnName: String = columns[i].toString()
                        val foreignKeyTableName: String = elements[0]
                        val foreignKeyColumnName: String = elements[1]
                        queryString += ", FOREIGN KEY ($columnName) REFERENCES $foreignKeyTableName($foreignKeyColumnName)"
                    }
                }
            }
            if (i != numberOfColumns - 1)
            {
                queryString += ","
            }
        }

        queryString += ");"

        return query(queryString)
    }

    @RegisterFunction
    fun dropTable(tableName : String) : Boolean {
        /* Create SQL statement */
        val queryString = "DROP TABLE $tableName;"

        return query(queryString)
    }

    @RegisterFunction
    fun insertRow(tableName : String, rowDictionary : Dictionary) : Boolean {

        var keyString = ""
        var valueString = ""
        val keys : GDArray = rowDictionary.keys
        val values : GDArray = rowDictionary.values

        var queryString = "INSERT INTO $tableName"

        val numberOfKeys : Int = rowDictionary.size()

        for (i in 0 until numberOfKeys)
        {
            keyString += keys[i]
            if (values[i]?.getType() == Variant.Type.STRING)
            {
                valueString += "'" + values[i] + "'"
            }
            else
            {
                valueString += values[i]
            }
            if (i != numberOfKeys - 1)
            {
                keyString += ","
                valueString += ","
            }
        }
        queryString += " ($keyString) VALUES ($valueString);"

        return query(queryString)
    }

    @RegisterFunction
    fun insertRows(tableName : String, rowArray : GDArray) : Boolean {
        val numberOfRows : Int = rowArray.size()
        for (i in 0 until numberOfRows)
        {
            if (rowArray[i]?.getType() != Variant.Type.DICTIONARY)
            {
                GD.print("GDSQLite Error: All elements of the Array should be of type Dictionary")
                return false
            }
            if (!insertRow(tableName, rowArray[i]?.toDictionary()!!))
            {
                return false
            }
        }
        return true
    }

    @RegisterFunction
    fun selectRows(tableName : String, conditions : String, columnsArray : GDArray) : GDArray {
        val numberOfColumns : Int = columnsArray.size()

        /* Create SQL statement */
        var queryString = "SELECT "
        for (i in 0 until numberOfColumns)
        {
            if (columnsArray[i]?.getType() != Variant.Type.STRING)
            {
                GD.print("GDSQLite Error: All elements of the Array should be of type String")
                return GDArray()
                //return queryResult
            }
            queryString += columnsArray[i].toString()

            if (i != numberOfColumns - 1)
            {
                queryString += ","
            }
        }
        queryString += " FROM $tableName"
        if (conditions.isNotEmpty())
        {
            queryString += " WHERE $conditions"
        }
        queryString += ";"

        query(queryString)
        return queryResult.duplicate(true)
    }

    @RegisterFunction
    fun updateRows(tableName : String, conditions : String, updatedRowDictionary : Dictionary) : Boolean {

        val numberOfKeys : Int = updatedRowDictionary.size()
        val keys : GDArray = updatedRowDictionary.keys
        val values : GDArray = updatedRowDictionary.values

        /* Create SQL statement */
        var queryString = "UPDATE $tableName SET "

        for (i in 0 until numberOfKeys)
        {
            queryString += "" + keys[i] + "="
            if (values[i]?.getType() == Variant.Type.STRING)
            {
                queryString += "'" + values[i] + "'"
            }
            else
            {
                queryString += values[i]
            }

            if (i != numberOfKeys - 1)
            {
                queryString += ","
            }
        }
        queryString += " WHERE $conditions;"

        return query(queryString)
    }

    @RegisterFunction
    fun deleteRows(tableName : String, conditions : String) : Boolean {
        /* Create SQL statement */
        var queryString = "DELETE FROM $tableName"
        /* If it's empty or * everything is to be deleted */
        if (conditions.isNotEmpty() && conditions != "*")
        {
            queryString += " WHERE $conditions"
        }
        queryString += ";"

        return query(queryString)
    }

    private fun validateJSON(importJSON : GDArray, tablesToImport : MutableList<TableData>) : Boolean {
        /* Start going through all the tables and checking their validity */
        val numberOfTables : Int = importJSON.size()
        for (i in 0 until numberOfTables - 1)
        {

            /* Create a new table_struct */
            val newTable = TableData ()
            val tempDictionary : Dictionary = importJSON[i]?.toDictionary()!!
            /* Get the name of the table */
            if (!tempDictionary.has("name"))
            {
                /* Did not find the necessary key! */
                GD.print("GDSQlite Error: Did not find required key \"name\" in the supplied json-file")
                return false
            }
            newTable.name = tempDictionary["name"].toString()

            /* Extract the sql template for generating the table */
            if (!tempDictionary.has("sql"))
            {
                /* Did not find the necessary key! */
                GD.print("GDSQlite Error: Did not find required key \"sql\" in the supplied json-file")
                return false
            }
            newTable.sql = tempDictionary["sql"].toString()

            if (!tempDictionary.has("row_array"))
            {
                /* Did not find the necessary key! */
                GD.print("GDSQlite Error: Did not find required key \"row_array\" in the supplied json-file")
                return false
            }
            if (tempDictionary["row_array"]?.getType()  != Variant.Type.ARRAY)
            {
                GD.print("GDSQlite Error: The value of the key \"row_array\" should consist of an array of rows")
                return false
            }
            newTable.rowArray = tempDictionary["row_array"]?.toGDArray()!!

            /* Add the table to the new tables vector */
            tablesToImport.add(tablesToImport.size, newTable)
        }
        return true
    }

}