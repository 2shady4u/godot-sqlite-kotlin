package godot_kotlin_sqlite

import godot.Reference
import godot.ProjectSettings
import godot.core.*
import kotlinx.cinterop.*
import sqlite3.*

fun callback(closure: COpaquePointer?, argc: Int, argv: CPointer<CPointerVar<ByteVar>>?, azColName: CPointer<CPointerVar<ByteVar>>?): Int {

    var columnDict = Dictionary()
    /* Get a reference to the instanced object */
    val stableRef = closure?.asStableRef<SQLiteWrapper>()
    val obj = stableRef?.get()
    var stmt: CPointer<sqlite3_stmt>? = sqlite3_next_stmt(obj?.db, null)
    var columnValue: Variant

    /* Loop over all columns and add them to the Dictionary */
    for (i in 0..argc) {
        /* Check the column type and do correct casting */
        columnValue = when (sqlite3_column_type(stmt, i)) {
            SQLITE_INTEGER -> {
                Variant(sqlite3_column_int64(stmt, i).toInt())
            }
            SQLITE_FLOAT -> {
                Variant(sqlite3_column_double(stmt, i))
            }
            SQLITE_TEXT -> {
                Variant(sqlite3_column_text (stmt, i).toString())
            }
            else -> {
                Variant(sqlite3_column_text (stmt, i).toString())
            }
        }
        //columnDict[azColName[i].toString()] = columnValue
    }
    /* Add result to query_result Array */
    obj?.query_result?.append(Variant(columnDict))

    /* cleanup! */
    stableRef?.dispose()
    return 0

}

class SQLiteWrapper : Reference {
    constructor() : super()

    var db : CPointer<sqlite3>? = null
    var path : String = "default"
    var error_message : String = ""
    var verbose_mode : Boolean = false
    val query_result : GDArray = GDArray()

    fun getVersion() : String {
        return "Hello Godot!, My version is " + sqlite3_libversion_number().toString()
    }

    fun openDatabase() : Boolean {
        var rc = 0
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

        if (rc == 1)
        {
            GD.print("GDSQLite Error: Can't open database: " + sqlite3_errmsg(db).toString());
            return false
        }
        else {
            GD.print("Opened database successfully ($path)")
        }

        return true
    }

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

    fun query(p_query : String) : Boolean {
        var zErrMsg : CPointer<ByteVar>? = null
        var rc = 0

        if (verbose_mode)
        {
            GD.print(p_query);
        }
        /* Clear the previous query results */
        query_result.clear()

        val stableRef = StableRef.create(this)
        val voidptr = stableRef.asCPointer()
        /* Execute SQL statement */
        memScoped {
            val q: CPointerVar<ByteVar> = alloc()
            rc = sqlite3_exec(db, p_query, staticCFunction(::callback), voidptr, q.ptr)
            zErrMsg = q.value
        }
        /* cleanup! */
        stableRef.dispose()

        error_message = zErrMsg.toString();
        if (rc != SQLITE_OK)
        {
            GD.print(" --> SQL error: $error_message")
            sqlite3_free(zErrMsg)
            return false
        }
        else if (verbose_mode)
        {
            GD.print(" --> Query succeeded")
        }
        return true
    }
}