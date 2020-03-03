package org.godotengine.kotlin

import godot.gdnative.godot_gdnative_init_options
import godot.gdnative.godot_gdnative_terminate_options
import godot.gdnative.godot_wrapper_gdnative_init
import godot.gdnative.godot_wrapper_gdnative_terminate
import godot.gdnative.godot_wrapper_nativescript_init
import kotlin.native.CName
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.get
import kotlinx.cinterop.ptr

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
}
