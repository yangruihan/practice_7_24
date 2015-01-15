# -- coding:utf-8 --
#
# filename:'ex_filter_test1.py'
#

import math

def is_prime_num(num):
	if 1 == num or 0 == num:
		return False
	for i in range(2, int(math.sqrt(num))):
		if 0 == num % i:
			return False
	return True

L = range(1, 101)

print filter(is_prime_num, L)
