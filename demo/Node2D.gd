extends Node2D

const SQLite = preload("res://addons/godot-kotlin-sqlite/bin/SQLiteWrapper.gdns")


# Called when the node enters the scene tree for the first time.
func _ready():
	
	var db = SQLite.new()
	
	db.verbose_mode = true
	db.path = "database"
	
	print(db.getVersion())
	
	print(db.openDatabase())
	
	db.query("CREATE TABLE IF NOT EXISTS contacts (contact_id INTEGER PRIMARY KEY,first_name TEXT NOT NULL,last_name TEXT NOT NULL);")
	db.query("INSERT INTO contcts (contact_id, first_name, last_name) VALUES(2, 'Albert', 'Einstein');")
	
	db.closeDatabase()
