# -- coding: utf-8 --
#
#filename:'ex6.py'
#

x = "There are %d types of people." % 10
binary = "binary"
do_not = "don't"
y = "Those who know %s and those who %s." % (binary, do_not) # 1

print x
print y

print "I said: %r." % x # 2
print "I also said:'%s'." % y # 3 

hilarious = False
joke_evaluation = "Isn't that joke so funny?! %r"

print joke_evaluation % hilarious # 4

w = "This is the left side of..."
e = "a string with a right side."

print w + e
