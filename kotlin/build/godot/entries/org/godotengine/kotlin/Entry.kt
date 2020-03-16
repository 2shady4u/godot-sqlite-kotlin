package org.godotengine.kotlin

import godot.core.Variant
import godot.gdnative.godot_gdnative_init_options
import godot.gdnative.godot_gdnative_terminate_options
import godot.gdnative.godot_wrapper_gdnative_init
import godot.gdnative.godot_wrapper_gdnative_terminate
import godot.gdnative.godot_wrapper_nativescript_init
import godot.registration.constructFromRawMem
import godot.registration.deconstructFromRawMem
import godot.registration.get
import godot.registration.invoke
import godot.registration.noMethodToInvoke
import godot.registration.registerClass
import godot.registration.registerMethod
import godot.registration.registerProperty
import godot.registration.set
import godot_kotlin_sqlite.SQLiteWrapper
import kotlin.Int
import kotlin.Unit
import kotlin.native.CName
import kotlinx.cinterop.CFunction
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.get
import kotlinx.cinterop.ptr
import kotlinx.cinterop.staticCFunction

private fun constructorFunction0Constructor(): SQLiteWrapper {
  return godot_kotlin_sqlite.SQLiteWrapper()
}

private fun constructorBridge0(): CPointer<CFunction<(COpaquePointer?) -> COpaquePointer?>> {
  return staticCFunction { mem -> constructFromRawMem(mem, ::constructorFunction0Constructor) }
}

private fun destructorBridge0(): CPointer<CFunction<(COpaquePointer?) -> Unit>> {
  return staticCFunction { mem -> deconstructFromRawMem<godot_kotlin_sqlite.SQLiteWrapper>(mem) }
}

private fun internalFunctionOf0Registration0(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("__yieldSignalListener", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        args!!
        val arg0 = Array(numArgs - 0) { i -> godot.core.Variant(args[i + 0]!!) }
        obj.__yieldSignalListener(*arg0)
        return@run null
      }
    }
  }
}

private fun functionBridge0(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("openDatabase", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, _ ->
      run {
        when (numArgs) {
          0 -> {
            return@run Variant from obj.openDatabase()
          } else -> {
            noMethodToInvoke("openDatabase", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge1(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("importFromJSON", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          1 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            return@run Variant from obj.importFromJSON(arg0)
          } else -> {
            noMethodToInvoke("importFromJSON", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge2(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("exportToJSON", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          1 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            return@run Variant from obj.exportToJSON(arg0)
          } else -> {
            noMethodToInvoke("exportToJSON", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge3(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("closeDatabase", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, _ ->
      run {
        when (numArgs) {
          0 -> {
            obj.closeDatabase()
            return@run null
          } else -> {
            noMethodToInvoke("closeDatabase", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge4(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("query", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          1 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            return@run Variant from obj.query(arg0)
          } else -> {
            noMethodToInvoke("query", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge5(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("createTable", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          2 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            val arg1 = godot.core.Variant(args[1]!!).toDictionary()
            return@run Variant from obj.createTable(arg0, arg1)
          } else -> {
            noMethodToInvoke("createTable", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge6(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("dropTable", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          1 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            return@run Variant from obj.dropTable(arg0)
          } else -> {
            noMethodToInvoke("dropTable", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge7(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("insertRow", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          2 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            val arg1 = godot.core.Variant(args[1]!!).toDictionary()
            return@run Variant from obj.insertRow(arg0, arg1)
          } else -> {
            noMethodToInvoke("insertRow", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge8(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("insertRows", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          2 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            val arg1 = godot.core.Variant(args[1]!!).toGDArray()
            return@run Variant from obj.insertRows(arg0, arg1)
          } else -> {
            noMethodToInvoke("insertRows", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge9(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("selectRows", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          3 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            val arg1 = godot.core.Variant(args[1]!!).toString()
            val arg2 = godot.core.Variant(args[2]!!).toGDArray()
            return@run Variant from obj.selectRows(arg0, arg1, arg2)
          } else -> {
            noMethodToInvoke("selectRows", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge10(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("updateRows", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          3 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            val arg1 = godot.core.Variant(args[1]!!).toString()
            val arg2 = godot.core.Variant(args[2]!!).toDictionary()
            return@run Variant from obj.updateRows(arg0, arg1, arg2)
          } else -> {
            noMethodToInvoke("updateRows", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

private fun functionBridge11(): CPointer<CFunction<(
  COpaquePointer?,
  COpaquePointer?,
  Int,
  COpaquePointer?
) -> Unit>> {
  return staticCFunction { returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer ->
    invoke<godot_kotlin_sqlite.SQLiteWrapper>("deleteRows", returnValuePointer, rawObjectPointer, numberOfArguments, argumentsPointer) { obj, numArgs, args ->
      run {
        when (numArgs) {
          2 -> {
            args!!
            val arg0 = godot.core.Variant(args[0]!!).toString()
            val arg1 = godot.core.Variant(args[1]!!).toString()
            return@run Variant from obj.deleteRows(arg0, arg1)
          } else -> {
            noMethodToInvoke("deleteRows", "godot_kotlin_sqlite.SQLiteWrapper", numArgs)
            return@run null
          }
        }
      }
    }
  }
}

fun propertyGetterFunctionBinding0(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, returnValuePointer ->
    get<godot_kotlin_sqlite.SQLiteWrapper>("path", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, returnValuePointer) { objectValue ->
      Variant(objectValue.path)
    }
  }
}

fun propertySetterFunctionBinding0(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, valuePointer ->
    set<godot_kotlin_sqlite.SQLiteWrapper>("path", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, valuePointer) { objectValue, value ->
      objectValue.path = value.toString()
    }
  }
}

fun defaultValueGetter0(): Variant {
  return Variant("default")
}

fun propertyGetterFunctionBinding1(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, returnValuePointer ->
    get<godot_kotlin_sqlite.SQLiteWrapper>("errorMessage", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, returnValuePointer) { objectValue ->
      Variant(objectValue.errorMessage)
    }
  }
}

fun propertySetterFunctionBinding1(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, valuePointer ->
    set<godot_kotlin_sqlite.SQLiteWrapper>("errorMessage", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, valuePointer) { objectValue, value ->
      objectValue.errorMessage = value.toString()
    }
  }
}

fun defaultValueGetter1(): Variant {
  return Variant("")
}

fun propertyGetterFunctionBinding2(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, returnValuePointer ->
    get<godot_kotlin_sqlite.SQLiteWrapper>("verboseMode", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, returnValuePointer) { objectValue ->
      Variant(objectValue.verboseMode)
    }
  }
}

fun propertySetterFunctionBinding2(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, valuePointer ->
    set<godot_kotlin_sqlite.SQLiteWrapper>("verboseMode", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, valuePointer) { objectValue, value ->
      objectValue.verboseMode = value.toBoolean()
    }
  }
}

fun defaultValueGetter2(): Variant {
  return Variant(false)
}

fun propertyGetterFunctionBinding3(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, returnValuePointer ->
    get<godot_kotlin_sqlite.SQLiteWrapper>("foreignKeys", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, returnValuePointer) { objectValue ->
      Variant(objectValue.foreignKeys)
    }
  }
}

fun propertySetterFunctionBinding3(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, valuePointer ->
    set<godot_kotlin_sqlite.SQLiteWrapper>("foreignKeys", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, valuePointer) { objectValue, value ->
      objectValue.foreignKeys = value.toBoolean()
    }
  }
}

fun defaultValueGetter3(): Variant {
  return Variant(false)
}

fun propertyGetterFunctionBinding4(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, returnValuePointer ->
    get<godot_kotlin_sqlite.SQLiteWrapper>("queryResult", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, returnValuePointer) { objectValue ->
      Variant(objectValue.queryResult)
    }
  }
}

fun propertySetterFunctionBinding4(): CPointer<CFunction<(COpaquePointer?, COpaquePointer?) ->
    Unit>> {
  return staticCFunction { objectPointer, valuePointer ->
    set<godot_kotlin_sqlite.SQLiteWrapper>("queryResult", "godot_kotlin_sqlite.SQLiteWrapper", objectPointer, valuePointer) { objectValue, value ->
      objectValue.queryResult = value.toGDArray()
    }
  }
}

fun defaultValueGetter4(): Variant {
  return Variant(godot.core.GDArray())
}

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
  registerClass("godot_kotlin_sqlite.SQLiteWrapper", "Reference", constructorBridge0(), destructorBridge0())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "__yieldSignalListener", internalFunctionOf0Registration0())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "openDatabase", functionBridge0())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "importFromJSON", functionBridge1())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "exportToJSON", functionBridge2())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "closeDatabase", functionBridge3())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "query", functionBridge4())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "createTable", functionBridge5())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "dropTable", functionBridge6())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "insertRow", functionBridge7())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "insertRows", functionBridge8())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "selectRows", functionBridge9())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "updateRows", functionBridge10())
  registerMethod("godot_kotlin_sqlite.SQLiteWrapper", "deleteRows", functionBridge11())
  registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "path", false, propertyGetterFunctionBinding0(), propertySetterFunctionBinding0(), defaultValueGetter0())
  registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "errorMessage", false, propertyGetterFunctionBinding1(), propertySetterFunctionBinding1(), defaultValueGetter1())
  registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "verboseMode", false, propertyGetterFunctionBinding2(), propertySetterFunctionBinding2(), defaultValueGetter2())
  registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "foreignKeys", false, propertyGetterFunctionBinding3(), propertySetterFunctionBinding3(), defaultValueGetter3())
  registerProperty("godot_kotlin_sqlite.SQLiteWrapper", "queryResult", false, propertyGetterFunctionBinding4(), propertySetterFunctionBinding4(), defaultValueGetter4())
}
