# -- coding:utf-8 --
#
# filename:'ex_map_reduce_test2.py'
#

def mul(x, y):
	return x * y

def prod(l):
	return reduce(mul, l)

test1_input = [1, 2, 3, 4, 5]
test2_input = [9, 8 ,1, 0 ,1]

print prod(test1_input)
print prod(test2_input)