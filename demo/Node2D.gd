extends Node2D

const XXX = preload("res://addons/godot-kotlin-sqlite/bin/gdsqlite.gdns")


# Called when the node enters the scene tree for the first time.
func _ready():
	
	var YYY = XXX.new()
	
	print(YYY.getVersion())
	
#	print(YYY.openDatabase())
