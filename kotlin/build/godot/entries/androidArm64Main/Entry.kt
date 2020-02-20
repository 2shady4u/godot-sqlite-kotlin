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
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "getVersion", udcBridge2())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "openDatabase", udcBridge3())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "closeDatabase", udcBridge4())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "query", udcBridge5())
    registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "__yieldSignalListener", udcBridge6())
    registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "verbose_mode", true, udcBridge7(), udcBridge8(), udcBridge9())
    registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "path", true, udcBridge10(), udcBridge11(), udcBridge12())
    registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "query_result", true, udcBridge13(), udcBridge14(), udcBridge15())
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
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("getVersion", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            0 -> {
                return@run Variant from obj.getVersion()
            }
            else -> noMethodToInvoke("getVersion", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge3(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
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

private fun udcBridge4(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
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

private fun udcBridge5(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
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

private fun udcBridge6(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<godot_kotlin_sqlite.SQLiteWrapper>("__yieldSignalListener", r, o, n, a) { obj, numArgs, args -> run {
        args!!
        val varargs = Array(numArgs) { i -> Variant(args[i]!!) }
        obj.__yieldSignalListener(*varargs)
        null
    }}}
}

private fun udcBridge7(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, r -> get<godot_kotlin_sqlite.SQLiteWrapper>("verbose_mode", "godot_kotlin_sqlite.SQLiteWrapper", o, r) {
        obj -> Variant(obj.verbose_mode)
    }}
}
private fun udcBridge8(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, v -> set<godot_kotlin_sqlite.SQLiteWrapper>("verbose_mode", "godot_kotlin_sqlite.SQLiteWrapper", o, v) {
        obj, value -> obj.verbose_mode = value.toBoolean()
    }}
}
private fun udcBridge9(): Variant {
    return Variant()
}
private fun udcBridge10(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, r -> get<godot_kotlin_sqlite.SQLiteWrapper>("path", "godot_kotlin_sqlite.SQLiteWrapper", o, r) {
        obj -> Variant(obj.path)
    }}
}
private fun udcBridge11(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, v -> set<godot_kotlin_sqlite.SQLiteWrapper>("path", "godot_kotlin_sqlite.SQLiteWrapper", o, v) {
        obj, value -> obj.path = value.toString()
    }}
}
private fun udcBridge12(): Variant {
    return Variant("")
}
private fun udcBridge13(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, r -> get<godot_kotlin_sqlite.SQLiteWrapper>("query_result", "godot_kotlin_sqlite.SQLiteWrapper", o, r) {
        obj -> Variant(obj.query_result)
    }}
}
private fun udcBridge14(): CPointer<CFunction<(COpaquePointer?,COpaquePointer?) -> Unit>> {
    return staticCFunction { o, v -> set<godot_kotlin_sqlite.SQLiteWrapper>("query_result", "godot_kotlin_sqlite.SQLiteWrapper", o, v) {
        obj, value -> obj.query_result = value.toGDArray()
    }}
}
private fun udcBridge15(): Variant {
    return Variant()
}
