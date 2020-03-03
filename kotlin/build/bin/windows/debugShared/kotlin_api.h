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
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_godot_core_Dictionary;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_godot_kotlin_sqlite_TableData;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_kotlin_Any;
typedef struct {
  kotlin_KNativePtr pinned;
} kotlin_kref_godot_core_Variant;

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
          const char* (*get_errorMessage)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_errorMessage)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* set);
          kotlin_KBoolean (*get_foreignKeys)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_foreignKeys)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, kotlin_KBoolean set);
          const char* (*get_path)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_path)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* set);
          kotlin_kref_godot_core_GDArray (*get_queryResult)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_queryResult)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, kotlin_kref_godot_core_GDArray set);
          kotlin_KBoolean (*get_verboseMode)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          void (*set_verboseMode)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, kotlin_KBoolean set);
          void (*closeDatabase)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          kotlin_KBoolean (*createTable)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* tableName, kotlin_kref_godot_core_Dictionary tableDictionary);
          kotlin_KBoolean (*deleteRows)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* tableName, const char* conditions);
          kotlin_KBoolean (*dropTable)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* tableName);
          kotlin_KBoolean (*exportToJSON)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* exportPath);
          kotlin_KBoolean (*importFromJSON)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* importPath);
          kotlin_KBoolean (*insertRow)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* tableName, kotlin_kref_godot_core_Dictionary rowDictionary);
          kotlin_KBoolean (*insertRows)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* tableName, kotlin_kref_godot_core_GDArray rowArray);
          kotlin_KBoolean (*openDatabase)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz);
          kotlin_KBoolean (*query)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* queryString);
          kotlin_kref_godot_core_GDArray (*selectRows)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* tableName, const char* conditions, kotlin_kref_godot_core_GDArray columnsArray);
          kotlin_KBoolean (*updateRows)(kotlin_kref_godot_kotlin_sqlite_SQLiteWrapper thiz, const char* tableName, const char* conditions, kotlin_kref_godot_core_Dictionary updatedRowDictionary);
        } SQLiteWrapper;
        struct {
          kotlin_KType* (*_type)(void);
          kotlin_kref_godot_kotlin_sqlite_TableData (*TableData)(const char* name, const char* sql, kotlin_kref_godot_core_GDArray rowArray);
          const char* (*get_name)(kotlin_kref_godot_kotlin_sqlite_TableData thiz);
          void (*set_name)(kotlin_kref_godot_kotlin_sqlite_TableData thiz, const char* set);
          kotlin_kref_godot_core_GDArray (*get_rowArray)(kotlin_kref_godot_kotlin_sqlite_TableData thiz);
          void (*set_rowArray)(kotlin_kref_godot_kotlin_sqlite_TableData thiz, kotlin_kref_godot_core_GDArray set);
          const char* (*get_sql)(kotlin_kref_godot_kotlin_sqlite_TableData thiz);
          void (*set_sql)(kotlin_kref_godot_kotlin_sqlite_TableData thiz, const char* set);
          const char* (*component1)(kotlin_kref_godot_kotlin_sqlite_TableData thiz);
          const char* (*component2)(kotlin_kref_godot_kotlin_sqlite_TableData thiz);
          kotlin_kref_godot_core_GDArray (*component3)(kotlin_kref_godot_kotlin_sqlite_TableData thiz);
          kotlin_kref_godot_kotlin_sqlite_TableData (*copy)(kotlin_kref_godot_kotlin_sqlite_TableData thiz, const char* name, const char* sql, kotlin_kref_godot_core_GDArray rowArray);
          kotlin_KBoolean (*equals)(kotlin_kref_godot_kotlin_sqlite_TableData thiz, kotlin_kref_kotlin_Any other);
          kotlin_KInt (*hashCode)(kotlin_kref_godot_kotlin_sqlite_TableData thiz);
          const char* (*toString)(kotlin_kref_godot_kotlin_sqlite_TableData thiz);
        } TableData;
      } godot_kotlin_sqlite;
      struct {
        struct {
          struct {
            void (*GDNativeInit)(void* options);
            void (*GDNativeTerminate)(void* options);
            void (*NativeScriptInit)(void* handle);
            kotlin_kref_godot_core_Variant (*defaultValueGetter0)();
            kotlin_kref_godot_core_Variant (*defaultValueGetter1)();
            kotlin_kref_godot_core_Variant (*defaultValueGetter2)();
            kotlin_kref_godot_core_Variant (*defaultValueGetter3)();
            kotlin_kref_godot_core_Variant (*defaultValueGetter4)();
            void* (*propertyGetterFunctionBinding0)();
            void* (*propertyGetterFunctionBinding1)();
            void* (*propertyGetterFunctionBinding2)();
            void* (*propertyGetterFunctionBinding3)();
            void* (*propertyGetterFunctionBinding4)();
            void* (*propertySetterFunctionBinding0)();
            void* (*propertySetterFunctionBinding1)();
            void* (*propertySetterFunctionBinding2)();
            void* (*propertySetterFunctionBinding3)();
            void* (*propertySetterFunctionBinding4)();
          } kotlin;
        } godotengine;
      } org;
    } root;
  } kotlin;
} kotlin_ExportedSymbols;
extern kotlin_ExportedSymbols* kotlin_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_KOTLIN_H */
