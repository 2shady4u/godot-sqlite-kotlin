extends Node2D

const SQLite = preload("res://addons/godot-kotlin-sqlite/bin/SQLiteWrapper.gdns")


# Called when the node enters the scene tree for the first time.
func _ready():
	
	var db = SQLite.new()
	
	db.verbose_mode = true
	db.path = "database"
	
	print(db.getVersion())
	
	print(db.openDatabase())
	
	db.query("uuuu")
	
	db.closeDatabase()
