#ifndef KONAN_KOTLIN_H
#define KONAN_KOTLIN_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            kotlin_KBoolean;
#else
typedef _Bool           kotlin_KBoolean;
#endif
typedef unsigned short     kotlin_KChar;
typedef signed char        kotlin_KByte;
typedef short              kotlin_KShort;
typedef int                kotlin_KInt;
typedef long long          kotlin_KLong;
typedef unsigned char      kotlin_KUByte;
typedef unsigned short     kotlin_KUShort;
typedef unsigned int       kotlin_KUInt;
typedef unsigned long long kotlin_KULong;
typedef float              kotlin_KFloat;
typedef double             kotlin_KDouble;
typedef void*              kotlin_KNativePtr;
struct kotlin_KType;
typedef struct kotlin_KType kotlin_KType;

typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Byte;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Short;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Int;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Long;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Float;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Double;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Char;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Boolean;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Unit;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_godot_core_GDArray;

extern void godot_gdnative_init(void* options);
extern void godot_gdnative_terminate(void* options);
extern void godot_nativescript_init(void* handle);

typedef struct {
  /* Service functions. */
  void (*DisposeStablePointer)(kotlin_KNativePtr ptr);
  void (*DisposeString)(const char* string);
  kotlin_KBoolean (*IsInstance)(kotlin_KNativePtr ref, const kotlin_KType* type);
  kotlin_kref_kotlin_Byte (*createNullableByte)(kotlin_KByte);
  kotlin_kref_kotlin_Short (*createNullableShort)(kotlin_KShort);
  kotlin_kref_kotlin_Int (*createNullableInt)(kotlin_KInt);
  kotlin_kref_kotlin_Long (*createNullableLong)(kotlin_KLong);
  kotlin_kref_kotlin_Float (*createNullableFloat)(kotlin_KFloat);
  kotlin_kref_kotlin_Double (*createNullableDouble)(kotlin_KDouble);
  kotlin_kref_kotlin_Char (*createNullableChar)(kotlin_KChar);
  kotlin_kref_kotlin_Boolean (*createNullableBoolean)(kotlin_KBoolean);
  kotlin_kref_kotlin_Unit (*createNullableUnit)(void);

  /* User functions. */
  struct {
    struct {
      struct {
        kotlin_KInt (*callback)(void* closure, kotlin_KInt argc, void* argv, void* azColName);
        struct {
          kotlin_KType* (*_type)(void);
          kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper (*SQLiteWrapper)();
          void* (*get_db)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_db)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, void* set);
          const char* (*get_error_message)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_error_message)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* set);
          const char* (*get_path)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_path)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* set);
          kotlin_kref_godot_core_GDArray (*get_query_result)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_query_result)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, kotlin_kref_godot_core_GDArray set);
          kotlin_KBoolean (*get_verbose_mode)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_verbose_mode)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, kotlin_KBoolean set);
          void (*closeDatabase)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          const char* (*getVersion)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          kotlin_KBoolean (*openDatabase)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          kotlin_KBoolean (*query)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* p_query);
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
} kotlin_ExportedSymbols;
extern kotlin_ExportedSymbols* kotlin_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_KOTLIN_H */
