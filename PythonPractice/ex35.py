# -- coding: utf-8 --
#
#filename:'ex35.py'
#

from sys import exit

def gold_room():
	"""This is a room which is full of gold"""

	print "This room is full of gold.  How much do you take?"

	next = raw_input("> ")
	if int(next):
		how_much = int(next)
	else:
		dead("Man, learn to type a number.")

	if how_much < 50:
		print "Nice, you're not greedy, you win!"
		exit(0)
	else:
		dead("You greedy bastard!")

def bear_room():
	"""This is a room where there is a bear"""

	print "There is a bear here."
	print "The bear has a bunch of honey."
	print "The fat bear is in front of another door."
	print "How are you going to move the bear?"
	bear_moved = False;

	while True:
		next = raw_input("> ")

		if next == "take honey":
			dead("The  bear looks at you then slaps your face off.")
		elif next == "taunt bear" and not bear_moved:
			print "The bear has moved from the door. You can go through it now."
			bear_moved = True;
		elif next == "taunt bear" and bear_moved:
			dead("The bear gets pissed off and chews your leg off.")
		elif next == "open door" and bear_moved:
			gold_room()
		elif next == "hit bear" and not bear_moved:
			print "The bear become angry and run to you!"
			bear_moved = True
		elif next == "hit bear" and bear_moved:
			dead("The bear kills you.")
		else:
			print "I got no idea what that means."

def cthulhu_room():
	"""This is a room where there is the evil Cthulhu"""

	print "Here you see the great evil Cthulhu."
	print "He, it, whatever stares at you and you go insane."
	print "Do you flee for your life or eat your head?"

	next = raw_input("> ")

	if "flee" in next:
		start()
	elif "head" in next:
		dead("Well that was tasty!")
	else:
		cthulhu_room()

def dead(why):
	"""You die"""

	print why, "Good job!"
	exit(0)

def start():
	"""The beginning of the game"""

	print "You are in a dark room."
	print "There is a door to your right and left."
	print "Which one do you take?"

	next = raw_input("> ")

	if next == "left":
		bear_room()
	elif next == "right":
		cthulhu_room()
	else:
		dead("You stumble around the room util you starve.")

start()

