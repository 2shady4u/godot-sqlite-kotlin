extends Control

const SQLite = preload("res://addons/godot-kotlin-sqlite/bin/SQLiteWrapper.gdns")

onready var _label : Label = $VBoxContainer/Label

# Called when the node enters the scene tree for the first time.
func _ready():
	
	var db = SQLite.new()
	
	db.verbose_mode = true
	db.path = "res://database.db"
	
	print(db.getVersion())
	
	var file : File = File.new()
	if file.file_exists("res://database.db"):
		print("file exists!")
	else:
		print("file doesnt exist!")
	
	print(db.openDatabase())
	
	db.query("DROP TABLE contacts;")
	db.query("CREATE TABLE IF NOT EXISTS contacts (contact_id INTEGER PRIMARY KEY,name TEXT NOT NULL, balance FLOAT DEFAULT 0.0);")
	db.query("INSERT INTO contacts (contact_id, name, balance) VALUES(2, 'Albert Einstein', 0.0);")
	db.query("INSERT INTO contacts (contact_id, name, balance) VALUES(36, 'Isaac Newton', 2.6);")
	db.query("INSERT INTO contacts (contact_id, name, balance) VALUES(1, 'Isaac Asimov', 3000);")
	
	db.query("SELECT * FROM contacts;")
	
	var saved_query : Array = db.query_result
	saved_query = saved_query.duplicate(true)
	
	_label.text = to_json(saved_query)
	
	print(saved_query)
#	print(typeof(saved_query[0].balance))
#	print(typeof(saved_query[0].name))
#	print(typeof(saved_query[0].contact_id))
	
	db.closeDatabase()
