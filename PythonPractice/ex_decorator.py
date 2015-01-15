# -- coding:utf-8 --
#
# filename:'ex_decorator.py'

# ----------decorator----------

# 要增强一个函数的功能，又不想修改它的定义
# 这种在代码运行期间动态增加功能的方式，称之为“装饰器”（Decorator）

def log(func):
	def wrapper(*args, **kw):
		print 'call %s():' % func.__name__
		return func(*args, **kw)
	return wrapper

@log
def helloworld():
	print 'helloworld'

helloworld()
# result:
# call helloworld():
# helloworld

import functools

# 如果decorator本身也需要传入参数
def log(text):
	def decorator(func):
		@functools.wraps(func) # 将func.__name__ 赋值给 wrapper.__name__
		def wrapper(*args, **kw):
			print '%s %s:' % (text, func.__name__)
			return func(*args, **kw)
		return wrapper
	return decorator

@log('execute')
def now():
	print 'The time is 2015-1-15'

now()
# result:
# execute now:
# The time is 2015-1-15







