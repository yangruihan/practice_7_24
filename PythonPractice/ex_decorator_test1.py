# -- coding:utf-8 --
#
# filename:'ex_decorator_test1.py'
#

import functools

def log(text = 'hello'):
	def decorator(func):
		@functools.wraps(func)
		def wrapper(*args, **kw):
			print 'begin call'
			func(*args, **kw)
			print text
			print 'end call'
			return
		return wrapper
	return decorator

@log()
def now():
	print 'Now is 2015-1-15'

@log('execute')
def hello():
	print 'hello world'

now()
hello()
