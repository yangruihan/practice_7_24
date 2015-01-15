# -- coding:utf-8 --
#
# filename : 'test1.py'
#

# 函数本身也可以赋值给变量，即变量可以指向函数
# for example:
f = abs
print f
print f(-10)

# 函数名也是一个变量，只不过是指向函数体的变量
# 可以将函数名赋值，则无法调用原来指向的函数
# for example
# abs = 10
# print abs # 结果输出是10
# print abs(-10) # 会报错

# 高阶函数：将其他函数当做参数接收
# for example
def add(x, y, f):
	return f(x) + f(y)

print add(-20, -100, abs) # 结果为120

# 返回相反数
def opp(x):
	return -x

print add(100, -20, opp) #结果为-80

# map 函数
L = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]

def power2(x):
	return x*x











