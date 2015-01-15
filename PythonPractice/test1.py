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
abs = 10
print abs
# print abs(-10) # 会报错
