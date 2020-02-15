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
    registerClass("sqlite.Sqlite", "Node", udcBridge0(), udcBridge1())
    registerMethod("sqlite.Sqlite", "_ready", udcBridge2())
    registerMethod("sqlite.Sqlite", "openDatabase", udcBridge3())
    registerMethod("sqlite.Sqlite", "__yieldSignalListener", udcBridge4())
}



// Bindings
private fun udcBinding0Constructor(): sqlite.Sqlite = sqlite.Sqlite()
private val udcBinding0 = ::udcBinding0Constructor



// Bridges
private fun udcBridge0(): CPointer<CFunction<(COpaquePointer?) -> COpaquePointer?>> {
    return staticCFunction { mem -> constructFromRawMem(mem, udcBinding0) }
}
private fun udcBridge1(): CPointer<CFunction<(COpaquePointer?) -> Unit>> {
    return staticCFunction { mem -> deconstructFromRawMem<sqlite.Sqlite>(mem) }
}
private fun udcBridge2(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<sqlite.Sqlite>("_ready", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            0 -> {
                obj._ready()
            }
            else -> noMethodToInvoke("_ready", "sqlite.Sqlite", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge3(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<sqlite.Sqlite>("openDatabase", r, o, n, a) { obj, numArgs, args -> run {
        when (numArgs) {
            0 -> {
                return@run Variant from obj.openDatabase()
            }
            else -> noMethodToInvoke("openDatabase", "sqlite.Sqlite", numArgs)
        }
        return@run null
    }}}
}

private fun udcBridge4(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?, Int, COpaquePointer?) -> Unit>> {
    return staticCFunction { r, o, n, a -> invoke<sqlite.Sqlite>("__yieldSignalListener", r, o, n, a) { obj, numArgs, args -> run {
        args!!
        val varargs = Array(numArgs) { i -> Variant(args[i]!!) }
        obj.__yieldSignalListener(*varargs)
        null
    }}}
}

