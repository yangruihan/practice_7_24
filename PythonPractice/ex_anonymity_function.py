# -- coding:utf-8 --
#
# filename:'ex_anonymity_function.py'

# ----------anonymity_function----------

print map(lambda x:x * x, [1, 2, 3, 4, 5])
# result:[1, 4, 9, 16, 25]

# 关键字lambda表示匿名函数，冒号前面的x表示函数参数
f = lambda x:x * x
print f(3)
# result:9

