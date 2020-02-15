extends Node2D

const XXX = preload("res://Scripts/Sqlite.gdns")


# Called when the node enters the scene tree for the first time.
func _ready():
	
	var YYY = XXX.new()
	add_child(YYY)
	
#	print(YYY.openDatabase())
