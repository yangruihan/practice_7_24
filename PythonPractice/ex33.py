# -- coding: utf-8 --
#
#filename:'ex33.py'
#

i = 0
numbers = []

while i < 6:
	print "At the top i is %d" % i
	numbers.append(i)

	i += 1
	print "Numbers now: ", numbers
	print "At the bottom i is %d\n" % i

print "The numbers: "

for num in numbers:
	print num

for i in range(7):
	print "The late i is %d" % i
	i += 1
	print "Now i is %d\n" % i

