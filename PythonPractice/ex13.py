# -- coding: utf-8 --
#
#filename:'ex13.py'
#

from sys import argv
# import 后面的叫模组（modules） 或者库（library）

script, first, second, third = argv

color = raw_input("What's your favorite color?")

print "The script is called:", script
print "Your first variable is:", first
print "Your second variable is:", second
print "Your third variable is:",third
print "Your favorite color is %r" % color

