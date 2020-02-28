package godot_kotlin_sqlite

import godot.Reference
import godot.ProjectSettings
import godot.core.*
import kotlinx.cinterop.*
import sqlite3.*
import org.godotengine.kotlin.annotation.*

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

@RegisterClass("addons/godot-kotlin-sqlite/bin")
class SQLiteWrapper() : Reference() {

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
    fun testDuplicate() {
        var outer_dict = Dictionary()
        var duplicated_dict = Dictionary()
        var inner_dict = Dictionary()

        outer_dict["other_dictionary"] = inner_dict
        outer_dict["random_string"] = "The sky is blue!"

        inner_dict["some_other_string"] = "Sometimes the sky is also red!"

        GD.print(outer_dict.toJson())

        // Without any duplication.
        duplicated_dict = outer_dict
        outer_dict["random_string"] = "Money must flow!"
        GD.print(duplicated_dict.toJson())

        // With duplication that isn't deep.
        duplicated_dict = outer_dict.duplicate(false)
        outer_dict["random_string"] = "Godot is our lord and savior!"
        GD.print(duplicated_dict.toJson())

        // With duplication that isn't deep.
        duplicated_dict = outer_dict.duplicate(true)
        outer_dict["random_string"] = "There's a snake in my boot!"
        inner_dict["some_other_string"] = "'The man who shot Liberty Valence' is the best Western ever made!"
        GD.print(duplicated_dict.toJson())

    }

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
        val queryString : String = "DROP TABLE $tableName;"

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
                return queryResult
            }
            queryString += columnsArray[i].toString()

            if (i != numberOfColumns - 1)
            {
                queryString += ","
            }
        }
        queryString += " from $tableName"
        if (conditions.isNotEmpty())
        {
            queryString += " WHERE $conditions"
        }
        queryString += ";"

        query(queryString)
        return queryResult
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

}