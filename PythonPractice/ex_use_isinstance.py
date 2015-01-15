# -- coding: utf-8 --
# 
# filename:'ex_use_isinstance().py'
#

# 对于class的继承关系来说，使用type()就很不方便。
# 我们要判断class的类型，可以使用isinstance()函数。

class Animal(object):
	pass

class Dog(Animal):
	pass

class Husky(Dog):
	pass

a = Animal()
d = Dog()
h = Husky()

print isinstance(h, Husky)
print isinstance(h, Dog)
print isinstance(h, Animal)
# result:
# True
# True
# True

# 能用type()判断的基本类型也可以用isinstance()来判断
print isinstance('a', str)
print isinstance(u'a', unicode)
print isinstance('a', unicode)
print isinstance(123, int)
# result:
# True
# True
# False
# True

# 还能判断一个变量是否是某些变量中的一种
print isinstance('a', (str, unicode))
print isinstance(123, (int, str))
# result:
# True
# True

# str 和 unicode 都是从 basestring 继承下来的
















