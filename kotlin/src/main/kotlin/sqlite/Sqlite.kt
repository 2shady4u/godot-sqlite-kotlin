package sqlite

import godot.Node
import godot.core.GD
import godot.core.String
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.nativeNullPtr
import kotlinx.cinterop.value
import sqlite3.*
import kotlin.native.internal.NativePtr

class Sqlite : Node {
    constructor() : super()

    override fun _ready() {
        GD.print("Hello Godot!")
        GD.print(sqlite3_libversion_number().toString())
    }

    fun openDatabase(): Boolean {
        var rc: Int
        /* Add .db to the path String if not present */
        var databaseName = "database.db"
        //var db = CPointerVar<sqlite3>(NativePtr.NULL)

        /* Try to open the database */
        rc = sqlite3_open(databaseName, null)


        if (rc == 1)
        {
            //GD.print("GDSQLite Error: Can't open database: " + String(sqlite3_errmsg(db)));
            return false
        }
        else {
            GD.print("Opened database successfully ($databaseName)")
        }

        return true
    }

    /*bool SQLite::open_db()
    {

        char *zErrMsg = 0;
        int rc;
        /* Add .db to the path String if not present */
        String ending = String(".db");
        if (path != ":memory:" && !path.ends_with(ending))
        {
            path += ending;
        }
        /* Find the real path */
        path = ProjectSettings::get_singleton()->globalize_path(path.strip_edges());

        /* CharString object goes out-of-scope when not assigned explicitely */
        const CharString dummy_path = path.utf8();
        const char *char_path = dummy_path.get_data();

        /* Try to open the database */
        rc = sqlite3_open(char_path, &db);

        if (rc)
        {
            Godot::print("GDSQLite Error: Can't open database: " + String(sqlite3_errmsg(db)));
            return false;
        }
        else
        {
            Godot::print("Opened database successfully (" + path + ")");
        }

        /* Try to enable foreign keys. */
        if (foreign_keys)
        {
            rc = sqlite3_exec(db, "PRAGMA foreign_keys=on;", NULL, NULL, &zErrMsg);
            if (rc != SQLITE_OK)
            {
                Godot::print("GDSQLite Error: Can't enable foreign keys: " + String(zErrMsg));
                sqlite3_free(zErrMsg);
                return false;
            }
        }

        return true;
    }*/
}