# -- coding: utf-8 --
#
#filename:'plus19_1.py'
#

def add(*numbers):
	list1 = numbers
	print list1
	sum = 0;
	for i in list1:
		sum += i
	return sum

def average(*numbers):
	return add(*numbers) * 1.0  / len(numbers)

print average(1, 2, 3 ,4 ,5)
print average(1, 2, 3)

