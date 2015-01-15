# -- coding: utf-8 --
#
# filename:'ex_use_dir()'
#

# 如果要活的一个对象的所有属性和方法，可以使用dir()

print dir('ABC')
# result:
# ['__add__', '__class__', '__contains__', '__delattr__', '__doc__', '__eq__', '__format__', '__ge__', '__getattribute__', '__getitem__', '__getnewargs__', '__getslice__', '__gt__', '__hash__', '__init__', '__le__', '__len__', '__lt__', '__mod__', '__mul__', '__ne__', '__new__', '__reduce__', '__reduce_ex__', '__repr__', '__rmod__', '__rmul__', '__setattr__', '__sizeof__', '__str__', '__subclasshook__', '_formatter_field_name_split', '_formatter_parser', 'capitalize', 'center', 'count', 'decode', 'encode', 'endswith', 'expandtabs', 'find', 'format', 'index', 'isalnum', 'isalpha', 'isdigit', 'islower', 'isspace', 'istitle', 'isupper', 'join', 'ljust', 'lower', 'lstrip', 'partition', 'replace', 'rfind', 'rindex', 'rjust', 'rpartition', 'rsplit', 'rstrip', 'split', 'splitlines', 'startswith', 'strip', 'swapcase', 'title', 'translate', 'upper', 'zfill']

# 配合getattr()、setattr()、hasattr()，可以直接操作一个对象的状态
class MyObject(object):

	def __init__(self, num):
		self.x = num

	def power(self):
		return self.x * self.x

obj = MyObject(9)

print hasattr(obj, 'x') # obj 有属性'x'吗？
print hasattr(obj, 'y') # obj 有属性'y'吗？
if not hasattr(obj, 'y'):
	setattr(obj, 'y', 19) # 如果没有属性'y'，就设置一个并赋值为19
print hasattr(obj, 'y')
print getattr(obj, 'y')
# result:
# True
# False
# True
# 19
# getattr(obj, 'z') # 会报错
print getattr(obj, 'z', 404)
# result: 404

print hasattr(obj, 'power')
fn = getattr(obj, 'power')
print fn()
# result:
# True
# 81













