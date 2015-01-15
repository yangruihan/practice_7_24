# -- coding:utf-8 --
#
# filename:'ex_sorted.py'

# ----------sorted----------

L = [23, 16, 98, 10, 17]

print sorted(L)
# result:[10, 16, 17, 23, 98]

# sorted() 函数也是一个高阶函数，它可以接受一个比较函数
# 来实现自定义排序
# for example:

def reversed_cmp(x, y):
	if x > y:
		return -1
	elif x < y:
		return 1
	else:
		return 0

print sorted(L, reversed_cmp)
# result:[98, 23, 17, 16, 10]

Ls = ['ghqiwe', 'Vjqwle', 'qewBad', 'Baqewe']

print sorted(Ls)
# result:['Baqewe', 'Vjqwle', 'ghqiwe', 'qewBad']
# 大写 < 小写 A ... Z, a ... z 根据ASCII表进行比较



