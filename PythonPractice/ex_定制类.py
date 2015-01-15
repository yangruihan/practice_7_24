# -*- coding: utf-8 -*-
#
# filename:'ex_定制类'
#

#----------__str__/__repr__----------
class Student(object):

	def __init__(self, name):
		self.name = name

print Student('Yang')
# result:<__main__.Student object at 0x007DDC30>

class Student2(object):

	def __init__(self, name):
		self.name = name

	def __str__(self):
		return 'Student object (name: %s)' % self.name

print Student2('Yang')
# result:Student object (name: Yang)

s = Student2('Yang')
s 
# 命令行还是输出 <__main__.Student object at 0x007DDC30>
# 因为直接显示变量调用的是__repr__()
# 而用print打印出来调用的是__str__()

# 可以这么写
class Student3(object):

	def __init__(self, name):
		self.name = name

	def __str__(self):
		return 'Student object (name: %s)' % self.name

	__repr__ = __str__

s = Student3('Yang')
print s 
s # 此时命令行显示的就为 Student object (name: Yang)
# result:
# Student object (name: Yang)


#----------__iter__----------
# 如果一个类想被用于for ... in 循环，类似list或tuple那样
# 就必须实现一个__iter__()方法，该方法返回一个迭代对象
# 然后，Python的for循环就会不断调用该迭代对象的next()方法
# 拿到循环的下一个值，直到遇到StopIteration错误时退出循环。

class Fib(object):

	def __init__(self):
		self.a, self.b = 0 ,1 # 初始化两个计数器a, b

	def __iter__(self):
		return self # 实例本身就是迭代对象，故返回自己

	def next(self):
		self.a, self.b = self.b, self.a + self.b # 计算下一个值
		if self.a > 100000: # 退出循环的条件
			raise StopIteration()
		return self.a

for i in Fib():
	print i
# result:
# 1
# 1
# ...
# 46368
# 75025


#---------__getitem----------
# 要想像list那样直接按照下标取出元素，
# 需要实现__getitem__()方法
class Fib2(object):

	def __getitem__(self, n):
		a, b = 1, 1
		for x in range(n):
			a, b = b, a + b
		return a

f = Fib2()
print f[0]
print f[1]
print f[5]
print f[10]
# result:
# 1
# 1
# 8
# 89

# 若要实现像list中l[5: 10]这样的切片方法
# 则需要判断出入的参数是否为一个切片对象
class Fib3(object):

	def __getitem__(self, n):
		if isinstance(n, int): # 即传入的n是一个位置
			a, b = 1, 1
			for x in range(n):
				a, b = b, a + b
			return a
		if isinstance(n, slice): # 即传入的n是一个切片变量
			start = n.start
			stop = n.stop
			a, b = 1, 1
			L = []
			for x in range(stop):
				if x >= start:
					L.append(a)
				a, b = b, a + b
			return L

f = Fib3()
print f[3]
print f[0: 5]
print f[5: 10]
# result:
# 3
# [1, 1, 2, 3, 5]
# [8, 13, 21, 34, 55]

# 实现了切片功能，但是还有很多没有进行处理
# 如实现对step参数作处理：f[: 10: 2]
# 也没有对负数作处理
# 此外，如果把对象看成一个dict，__getitem__()
# 的参数可能是一个作为key的object
# 与之对应的还有__setitem__()，用来赋值
# __delitem__()，用来删除某个元素


#----------__getattr__----------
# 当调用不存在的属性时，Python解释器会试图调用
# __getattr__(self, 'xxx')来尝试获得属性
class Teacher(object):

	def __init__(self):
		pass

	def __getattr__(self, attr):
		if attr == 'major':
			return 'Data structure'

t = Teacher()
print t.major
# result: Data structure

print t.name
# result: None
# 在写了__getattr__()函数之后，再调用不存在的属性
# 就算__getattr__()函数内未做处理的属性，也不会再
# 提示错误，而是返回None，必须手动加上错误提示

class Teacher2(object):

	def __getattr__(self, attr):
		if attr == 'major':
			return 'Data structure'
		raise AttributeError('\'Teacher\' object has no attribute \'%s\'' % attr)

t2 = Teacher2()
print t2.major
# result: Data structure
# print t2.name # 会报错
# Traceback (most recent call last):[Decode error - output not utf-8]
#     [Decode error - output not utf-8]
#     raise AttributeError('\'Teacher\' object has no attribute \'%s\'' % attr)
# AttributeError: 'Teacher' object has no attribute 'name'


#----------__call__----------
# 直接对实例进行调用的方法

class People(object):

	def __init__(self, name):
		self.name = name

	def __call__(self):
		print 'My name is %s' % self.name

p = People('Yang.R.H')
p()
# result: My name is Yang.R.H


#----------callable()---------
# 通过callable()我们可以判断哪些对象可以被调用
print callable(Student('Yang'))　
print callable(max)
print callable(abs)
print callable(Teacher())
print callable([1, 2, 3])
print callable('String')
# result:
# False
# True
# True
# False
# False
# False














