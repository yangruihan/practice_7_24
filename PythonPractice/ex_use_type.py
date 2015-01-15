# -- coding: utf-8 --
# 
# filename:'ex_use_type().py'
#

class Animal(object):
	pass

print type(123)
print type('str')
print type(None)
# result:
# <type 'int'>
# <type 'str'>
# <type 'NoneType'>

a = Animal()

print type(abs)
print type(a)
# result:
# <type 'builtin_function_or_method'>
# <class '__main__.Animal'>

print type(type)
# result: <type 'type'>

import types

print type('abc') == types.StringType
print type(u'abc') == types.UnicodeType
print type([]) == types.ListType
print type(str) == types.TypeType
# result:
# True
# True
# True
# True

# 最后注意到有一种类型就叫TypeType，所有类型本身的类型就是TypeType
type(int) == type(str) == types.TypeType
# result: True



