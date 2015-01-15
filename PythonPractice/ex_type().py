# -*- coding: utf-8 -*-
#
# filename:'ex_type()'
#

# type()函数既可以返回一个对象的类型，
# 又可以创建出新的类型

def fn(self, name = 'World'):
	print 'Hello %s.' % name

Hello = type('Hello', (object,), dict(hello = fn))

h = Hello()
h.hello()
# result: Hello World

print type(Hello)
print type(h)
# result:
# <type 'type'>
# <class '__main__.Hello'>

# 要创建一个对象，type()函数依次传入3个参数：
# 1.class的名字
# 2.继承的父类集合
# 3.class的方法名称与函数绑定

# metaclass 元类
# 定义metaclass → 创建类 → 创建实例

# metaclass是创建类，所以必须从'type'类型派生
class ListMetaclass(type):

	def __new__(cls, name, bases, attrs):
		attrs['add'] = lambda self, value: self.append(value)
		return type.__new__(cls, name, bases, attrs)

class MyList(list):
	__metaclass__ = ListMetaclass # 指示使用ListMetaclass来定制类

# __new__()方法接收到的参数依次是：
# 1.当前准备创建的类的对象
# 2.类的名字
# 3.类继承的父类集合
# 4.类的方法集合
 
L = MyList()
L.add(10)
print L

















