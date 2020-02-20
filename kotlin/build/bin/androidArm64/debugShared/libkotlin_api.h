#ifndef KONAN_LIBKOTLIN_H
#define KONAN_LIBKOTLIN_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            libkotlin_KBoolean;
#else
typedef _Bool           libkotlin_KBoolean;
#endif
typedef unsigned short     libkotlin_KChar;
typedef signed char        libkotlin_KByte;
typedef short              libkotlin_KShort;
typedef int                libkotlin_KInt;
typedef long long          libkotlin_KLong;
typedef unsigned char      libkotlin_KUByte;
typedef unsigned short     libkotlin_KUShort;
typedef unsigned int       libkotlin_KUInt;
typedef unsigned long long libkotlin_KULong;
typedef float              libkotlin_KFloat;
typedef double             libkotlin_KDouble;
typedef void*              libkotlin_KNativePtr;
struct libkotlin_KType;
typedef struct libkotlin_KType libkotlin_KType;

typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Byte;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Short;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Int;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Long;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Float;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Double;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Char;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Boolean;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_kotlin_Unit;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper;
typedef struct {
  libkotlin_KNativePtr pinned;
} libkotlin_kref_godot_core_GDArray;

extern void godot_gdnative_init(void* options);
extern void godot_gdnative_terminate(void* options);
extern void godot_nativescript_init(void* handle);

typedef struct {
  /* Service functions. */
  void (*DisposeStablePointer)(libkotlin_KNativePtr ptr);
  void (*DisposeString)(const char* string);
  libkotlin_KBoolean (*IsInstance)(libkotlin_KNativePtr ref, const libkotlin_KType* type);
  libkotlin_kref_kotlin_Byte (*createNullableByte)(libkotlin_KByte);
  libkotlin_kref_kotlin_Short (*createNullableShort)(libkotlin_KShort);
  libkotlin_kref_kotlin_Int (*createNullableInt)(libkotlin_KInt);
  libkotlin_kref_kotlin_Long (*createNullableLong)(libkotlin_KLong);
  libkotlin_kref_kotlin_Float (*createNullableFloat)(libkotlin_KFloat);
  libkotlin_kref_kotlin_Double (*createNullableDouble)(libkotlin_KDouble);
  libkotlin_kref_kotlin_Char (*createNullableChar)(libkotlin_KChar);
  libkotlin_kref_kotlin_Boolean (*createNullableBoolean)(libkotlin_KBoolean);
  libkotlin_kref_kotlin_Unit (*createNullableUnit)(void);

  /* User functions. */
  struct {
    struct {
      struct {
        libkotlin_KInt (*callback)(void* closure, libkotlin_KInt argc, void* argv, void* azColName);
        struct {
          libkotlin_KType* (*_type)(void);
          libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper (*SQLiteWrapper)();
          void* (*get_db)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_db)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, void* set);
          const char* (*get_error_message)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_error_message)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* set);
          const char* (*get_path)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_path)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* set);
          libkotlin_kref_godot_core_GDArray (*get_query_result)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_query_result)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, libkotlin_kref_godot_core_GDArray set);
          libkotlin_KBoolean (*get_verbose_mode)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_verbose_mode)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, libkotlin_KBoolean set);
          void (*closeDatabase)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          const char* (*getVersion)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          libkotlin_KBoolean (*openDatabase)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          libkotlin_KBoolean (*query)(libkotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* p_query);
        } SQLiteWrapper;
      } godot_kotlin_sqlite;
      struct {
        struct {
          struct {
            void (*GDNativeInit)(void* options);
            void (*GDNativeTerminate)(void* options);
            void (*NativeScriptInit)(void* handle);
          } entry;
        } godot;
      } kotlin;
    } root;
  } kotlin;
} libkotlin_ExportedSymbols;
extern libkotlin_ExportedSymbols* libkotlin_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_LIBKOTLIN_H */
