@file:Suppress("PackageDirectoryMismatch", "FunctionName", "UNUSED_ANONYMOUS_PARAMETER")
package kotlin.godot.entry

import kotlin.native.CName
import kotlinx.cinterop.*
import godot.gdnative.*
import godot.core.*
import godot.*
import godot.registration.*


// NOTE THIS FILE IS AUTO-GENERATED


@CName("godot_gdnative_init")
fun GDNativeInit(options: godot_gdnative_init_options) {
    godot_wrapper_gdnative_init(options.ptr)
}


@CName("godot_gdnative_terminate")
fun GDNativeTerminate(options: godot_gdnative_terminate_options) {
    godot_wrapper_gdnative_terminate(options.ptr)
}


@CName("godot_nativescript_init")
fun NativeScriptInit(handle: COpaquePointer) {
    godot_wrapper_nativescript_init(handle)

    // Let's do some registration magic.
    registerClass("godot_kotlin_sqlite.SQLiteWrapper", "Reference", udcBridge0(), udcBridge1())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "openDatabase", udcBridge2())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "closeDatabase", udcBridge3())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "query", udcBridge4())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "createTable", udcBridge5())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "dropTable", udcBridge6())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "insertRow", udcBridge7())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "insertRows", udcBridge8())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "selectRows", udcBridge9())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "updateRows", udcBridge10())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "deleteRows", udcBridge11())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "__yieldSignalListener", udcBridge12())
    registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "path", true, udcBridge13(), udcBridge14(), udcBridge15())
    registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "errorMessage", true, udcBridge16(), udcBridge17(), udcBridge18())
    registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "verboseMode", true, udcBridge19(), udcBridge20(), udcBridge21())
    registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "foreignKeys", true, udcBridge22(), udcBridge23(), udcBridge24())
    registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "queryResult", true, udcBridge25(), udcBridge26(), udcBridge27())
}



// Bindings
private fun udcBinding0Constructor(): godot_kotlin_sqlite.SQLiteWrapper = godot_kotlin_sqlite.SQLiteWrapper()
private val udcBinding0 = ::udcBinding0Constructor



// Bridges
private fun udcBridge0(): CPointer<CFunction<(COpaquePointer?) -> COpaquePointer?>> {
    return staticCFunction { mem -> constructFromRawMem(mem, udcBinding0) }
}
private fun udcBridge1(): CPointer<CFunction<(COpaquePointer?) -> Unit>> {
    return staticCFunction { mem -> deconstructFromRawMem<godot_kotlin_sqlite.SQLiteWrapper>(mem) }
}
private fun udcBridge2(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("openDatabase", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            0 -> {
                return@run Variant from obj.openDatabase()
            }
            else -> noMethodToInvoke("openDatabase", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge3(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("closeDatabase", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            0 -> {
                obj.closeDatabase()
            }
            else -> noMethodToInvoke("closeDatabase", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge4(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("query", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            1 -> {
                args!!
                val arg0 = Variant(args[0]!!).toString()
                return@run Variant from obj.query(arg0)
            }
            else -> noMethodToInvoke("query", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge5(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("createTable", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            2 -> {
                args!!
                val arg0 = Variant(args[0]!!).toString()
                val arg1 = Variant(args[1]!!).toDictionary()
                return@run Variant from obj.createTable(arg0, arg1)
            }
            else -> noMethodToInvoke("createTable", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge6(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("dropTable", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            1 -> {
                args!!
                val arg0 = Variant(args[0]!!).toString()
                return@run Variant from obj.dropTable(arg0)
            }
            else -> noMethodToInvoke("dropTable", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge7(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("insertRow", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            2 -> {
                args!!
                val arg0 = Variant(args[0]!!).toString()
                val arg1 = Variant(args[1]!!).toDictionary()
                return@run Variant from obj.insertRow(arg0, arg1)
            }
            else -> noMethodToInvoke("insertRow", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge8(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("insertRows", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            2 -> {
                args!!
                val arg0 = Variant(args[0]!!).toString()
                val arg1 = Variant(args[1]!!).toGDArray()
                return@run Variant from obj.insertRows(arg0, arg1)
            }
            else -> noMethodToInvoke("insertRows", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge9(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("selectRows", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            3 -> {
                args!!
                val arg0 = Variant(args[0]!!).toString()
                val arg1 = Variant(args[1]!!).toString()
                val arg2 = Variant(args[2]!!).toGDArray()
                return@run Variant from obj.selectRows(arg0, arg1, arg2)
            }
            else -> noMethodToInvoke("selectRows", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge10(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("updateRows", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            3 -> {
                args!!
                val arg0 = Variant(args[0]!!).toString()
                val arg1 = Variant(args[1]!!).toString()
                val arg2 = Variant(args[2]!!).toDictionary()
                return@run Variant from obj.updateRows(arg0, arg1, arg2)
            }
            else -> noMethodToInvoke("updateRows", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge11(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("deleteRows", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            2 -> {
                args!!
                val arg0 = Variant(args[0]!!).toString()
                val arg1 = Variant(args[1]!!).toString()
                return@run Variant from obj.deleteRows(arg0, arg1)
            }
            else -> noMethodToInvoke("deleteRows", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge12(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("__yieldSignalListener", r, o, n, a) { obj, numArgs, args -> run {
        args!!
        val varargs = Array(numArgs) { i -> Variant(args[i]!!) }
        obj.__yieldSignalListener(*varargs)
        null
    }}}
}

private fun udcBridge13(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, r -> get<godot_kotlin_sqlite.SQLiteWrapper>("path", "godot_kotlin_sqlite.SQLiteWrapper", o, r) {
        obj -> Variant(obj.path)
    }}
}
private fun udcBridge14(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, v -> set<godot_kotlin_sqlite.SQLiteWrapper>("path", "godot_kotlin_sqlite.SQLiteWrapper", o, v) {
        obj, value -> obj.path = value.toString()
    }}
}
private fun udcBridge15(): Variant {
    return Variant("")
}
private fun udcBridge16(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, r -> get<godot_kotlin_sqlite.SQLiteWrapper>("errorMessage", "godot_kotlin_sqlite.SQLiteWrapper", o, r) {
        obj -> Variant(obj.errorMessage)
    }}
}
private fun udcBridge17(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, v -> set<godot_kotlin_sqlite.SQLiteWrapper>("errorMessage", "godot_kotlin_sqlite.SQLiteWrapper", o, v) {
        obj, value -> obj.errorMessage = value.toString()
    }}
}
private fun udcBridge18(): Variant {
    return Variant("")
}
private fun udcBridge19(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, r -> get<godot_kotlin_sqlite.SQLiteWrapper>("verboseMode", "godot_kotlin_sqlite.SQLiteWrapper", o, r) {
        obj -> Variant(obj.verboseMode)
    }}
}
private fun udcBridge20(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, v -> set<godot_kotlin_sqlite.SQLiteWrapper>("verboseMode", "godot_kotlin_sqlite.SQLiteWrapper", o, v) {
        obj, value -> obj.verboseMode = value.toBoolean()
    }}
}
private fun udcBridge21(): Variant {
    return Variant()
}
private fun udcBridge22(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, r -> get<godot_kotlin_sqlite.SQLiteWrapper>("foreignKeys", "godot_kotlin_sqlite.SQLiteWrapper", o, r) {
        obj -> Variant(obj.foreignKeys)
    }}
}
private fun udcBridge23(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, v -> set<godot_kotlin_sqlite.SQLiteWrapper>("foreignKeys", "godot_kotlin_sqlite.SQLiteWrapper", o, v) {
        obj, value -> obj.foreignKeys = value.toBoolean()
    }}
}
private fun udcBridge24(): Variant {
    return Variant()
}
private fun udcBridge25(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, r -> get<godot_kotlin_sqlite.SQLiteWrapper>("queryResult", "godot_kotlin_sqlite.SQLiteWrapper", o, r) {
        obj -> Variant(obj.queryResult)
    }}
}
private fun udcBridge26(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, v -> set<godot_kotlin_sqlite.SQLiteWrapper>("queryResult", "godot_kotlin_sqlite.SQLiteWrapper", o, v) {
        obj, value -> obj.queryResult = value.toGDArray()
    }}
}
private fun udcBridge27(): Variant {
    return Variant()
}
