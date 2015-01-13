# -- coding: utf-8 --
#
# filename = 'GuessNumber.py'

import random
import sys

print "Hello guy, welcome to the GuessNumber world."
print "Your have three chances to guess the number, which is random. (1 - 100)"
print "And after you guessing every time, I will give your some information to help you."

print "If you're right, you are the lucky man!"
print "Or try it again!"

a = random.randint(1, 100)

print "Now this is your first chance",
a1 = raw_input("> ")
if int(a1) == a:
	print "How lucky you are! You win!"
	sys.exit(0)
elif int(a1) < a:
	print "less"
else:
	print "greater"

print "Don't give up, this is your second chance",
a1 = raw_input("> ")
if int(a1) == a:
	print "How lucky you are! You win!"
	sys.exit(0)
elif int(a1) < a:
	print "less"
else:
	print "greater"

print "Try it again, this is your last chance",
a1 = raw_input("> ")
if int(a1) == a:
	print "How lucky you are! You win!"
	sys.exit(0)
elif int(a1) < a:
	print "less"
else:
	print "greater"

print "Sorry to say that you lose, the right answer is %d" % a

