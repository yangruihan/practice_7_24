# -- coding:utf-8 --
#
# filename:'ex_filter.py'

# ----------filter----------

# filter 过滤器
# 将函数作用于list中的每一个元素，根据其返回值是T/F决定元素的去留

# 判断是否为奇数
def is_odd(n):
	return 1 == n % 2

L = [1, 2, 3, 4, 5, 6, 7, 8, 9]

print filter(is_odd, L)
# result:[1, 3, 5, 7, 9]

