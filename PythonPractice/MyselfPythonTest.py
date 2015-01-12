# -- coding: utf-8 --
#
#filename:'MyselfPythonTest'
#

from random import randint

print "Now we can do something just for fun."

print "Welcome to Python World!"

print "There are some numbers for you.(1 - 6)"

print "If you guess right, you will win or you will die."

a = randint(1, 6)
b = randint(1, 6)
c = randint(1, 6)

print "Now give me your answer:",
s = raw_input("> ")

temp = s.split(" ")

a1 = int(temp[0])
a2 = int(temp[1])
a3 = int(temp[2])

if a1 == a and a2 == b and a3 == c:
	print "WOW! you're a lucky man! Congradulations!"
else:
	print "Sorry, your life are mine now!"

