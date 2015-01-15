# -- coding:utf-8 --
#
# filename:'partial_function'

# ----------Partial_function----------

# 简单总结functools.partial的作用就是，
# 把一个函数的某些参数给固定住（也就是设置默认值），
# 返回一个新的函数，调用这个新函数会更简单。

import functools

# 将int的进制数定死为2，即将2进制字符串转换成10进制数
int2 = functools.partial(int, base = 2)

print int2('10000000')
print int2('0111010')
# resule:
# 128
# 58

# 将10当做参数传入max函数中
maxx = functools.partial(max, 10)
print maxx(5, 7, 9)
print maxx(11, 16, 2)
# result:
# 10
# 16

