extends Control

const SQLite = preload("res://addons/godot-kotlin-sqlite/bin/SQLiteWrapper.gdns")

onready var _label : Label = $VBoxContainer/Label

var db
#var db_name = "user://test"
var db_name : String = "test"
#var db_name = "test"
var json_name : String = "data/test_backup"
var table_name : String = "company"
var other_table_name : String = "expenses"

var ids : Array = [1,2,3,4,5,6,7]
var names : Array = ["Paul","Allen","Teddy","Mark","Robert","Julia","Amanda"]
var ages : Array = [32,25,23,25,30,63,13]
var addresses : Array = ["California","Texas","Baltimore","Richmond","Texas","Atlanta","New-York"]
var salaries : Array = [20000.00,15000.00,20000.00,65000.00,65000.00,65000.00,65000.00]

func _ready():
	example_of_basic_database_querying()
	#example_of_in_memory_and_foreign_key_support()

# Basic example that goes over all the basic features available in the addon, such
# as creating and dropping tables, inserting and deleting rows and doing more elementary
# PRAGMA queries. 
func example_of_basic_database_querying():

	# Make a big table containing the variable types.
	var table_dict : Dictionary = Dictionary()
	table_dict["id"] = {"data_type":"int", "primary_key": true, "not_null": true}
	table_dict["name"] = {"data_type":"text", "not_null": true}
	table_dict["age"] = {"data_type":"int", "not_null": true}
	table_dict["address"] = {"data_type":"char(50)"}
	table_dict["salary"] = {"data_type":"real"}

	db = SQLite.new()
	db.path = db_name
	db.verboseMode = true
	# Open the database using the db_name found in the path variable
	db.openDatabase()
	# Throw away any table that was already present
	db.dropTable(table_name)
	# Create a table with the structure found in table_dict and add it to the database
	db.createTable(table_name, table_dict)

	var row_array : Array = []
	var row_dict : Dictionary = Dictionary()
	for i in range(0,ids.size()):
		row_dict["id"] = ids[i]
		row_dict["name"] = names[i]
		row_dict["age"] = ages[i]
		row_dict["address"] = addresses[i]
		row_dict["salary"] = salaries[i]
		row_array.append(row_dict.duplicate())

		# Insert a new row in the table
		db.insertRow(table_name, row_dict)
		row_dict.clear()
	print(row_array)

	# Select the id and age of the employees that are older than 30
	var select_condition : String = "age > 30"
	var selected_array : Array = db.selectRows(table_name, select_condition, ["id", "age"])
	print("condition: " + select_condition)
	print("result: ", selected_array)

	# Change name of 'Amanda' to 'Olga' and her age to 30
	db.updateRows(table_name, "name = 'Amanda'", {"AGE":30, "NAME":"Olga"})

	# Select the employee with the name Olga and with age 30
	select_condition = "name = 'Olga' and age = 30"
	selected_array = db.selectRows(table_name, select_condition, ["*"])
	print("condition: " + select_condition)
	print("result: ", selected_array)

	# Delete the employee named Olga
	db.deleteRows(table_name, "name = 'Olga'")

	# Select all employees
	select_condition = ""
	db.selectRows(table_name, select_condition, ["*"])
	#selected_array = db.selectRows(table_name, select_condition, ["*"])
#	print("condition: " + select_condition)
#	print("result: ", selected_array)
#	# Check the types of the values in the dictionary
#	print("Types of selected columns:")
#	print("salary: ", typeof(selected_array[0]["salary"]))
#	print("age:    ", typeof(selected_array[0]["age"]))
#	print("name:   ", typeof(selected_array[0]["name"]))

	# Delete all employees
	db.deleteRows(table_name, "*")

	# Add all employees again
	db.insertRows(table_name, row_array)

	# Do a normal query
	db.query("SELECT COUNT(*) AS 'number_of_employees' FROM " + table_name + ";")
	print("There are ", db.queryResult[0]["number_of_employees"], " employees in the company")

	db.query("PRAGMA encoding;")
	print("Current database encoding is: ", db.queryResult[0]["encoding"])

#	# Export the table to a json-file with a specified name
#	db.exportToJSON(json_name + "_new")

#	# Close the current database
#	db.closeDatabase()
#
#	# Import (and, consequently, open) a database from an old backup json-file
#	print("Overwriting database content with old backup...")
#	db.importFromJSON(json_name + "_old")
#
#	# Check which employees were present in this old json-file
#	select_condition = ""
#	selected_array = db.selectRows(table_name, select_condition, ["*"])
#	print("condition: " + select_condition)
#	print("result: ", selected_array)
#	# Check the types of the values in the dictionary
#	print("Types of selected columns:")
#	print("salary: ", typeof(selected_array[0]["salary"]))
#	print("age:    ", typeof(selected_array[0]["age"]))
#	print("name:   ", typeof(selected_array[0]["name"]))
#
#	# Import the data (in a destructive manner) from the new backup json-file
#	print("Overwriting database content again with latest backup...")
#	db.importFromJSON(json_name + "_new")

	# Try to delete a non-existant table from the database.
	if not db.deleteRows(other_table_name, "*"):
		print("SQL error: " + db.errorMessage)

	# Close the imported database
	db.closeDatabase()

# This example demonstrates the in-memory and foreign key support. It's 
# rather contrived, but it gets the point across.
func example_of_in_memory_and_foreign_key_support():

	# Create the database as usual.
	db = SQLite.new()
	# Enable in-memory storage.
	db.path = ":memory:"
	db.verboseMode = true
	# Enable foreign keys.
	db.foreignKeys = true
	# Open the database as usual.
	db.openDatabase()

	# Create a table for all your friends.
	db.createTable("friends", {
		"id": {"data_type": "int", "primary_key": true, "not_null": true},
		"name": {"data_type": "text", "not_null": true, "unique": true},
		"hobby": {"data_type": "int", "foreign_key": "hobbies.id", "not_null": true}
	})

	# Create a table for all your friends' hobbies.
	db.createTable("hobbies", {
		"id": {"data_type": "int", "primary_key": true, "not_null": true},
		"description": {"data_type": "text", "not_null": true, "unique": true}
	})

	# ATTENTION: The important thing to note about the "friends" table is the 
	# definition of the foreign key "hobbies.id". This tells SQLITE to enforce 
	# the foreign key constraint, and that the field "friends.hobby" is now 
	# tied to the field "hobbies.id". Consequently, you are now required to 
	# specify a valid hobby when adding a friend to the database, which in 
	# turn means you first need to add some hobbies to the database before 
	# you can add any of your friends and assign them a hobby.
	
	# This won't work! There is no valid hobby with id 23 yet!
	db.insertRows("friends", [
		{"id": 1, "name": "John", "hobby": 23}
	])

	# This will work! You create the hobby with id 23 first, then you can 
	# create your friend referencing that hobby.
	db.insertRows("hobbies", [
		{"id": 23, "description": "Extreme Relaxing"}
	])
	db.insertRows("friends", [
		{"id": 1, "name": "John", "hobby": 23}
	])

	# Close the database.
	db.closeDatabase()
