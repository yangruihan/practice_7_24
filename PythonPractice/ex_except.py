# -*- coding: utf-8 -*-
#
# filename:'ex_except.py'
#

try:
	print 'try...'
	r = 10 / 0
	print 'result:', r
except ZeroDivisionError, e:
	print 'except:', e
finally:
	print 'finally...'

print 'END'
# result:
# try...
# except: integer division or modulo by zero
# finally...
# END

try:
	print 'try...'
	r = 10 / 5
	print 'result:', r
except ZeroDivisionError, e:
	print 'except:', e
finally:
	print 'finally...'

print 'END'
# result:
# try...
# result: 2
# finally...
# END

try:
	print 'try...'
	r = 10 / int('a')
	print 'result:', r
except ValueError, e:
	print 'ValueError:', e
except ZeroDivisionError, e:
	print 'ZeroDivisionError:', e
finally:
	print 'finally...'
print 'END'
# result:
# try...
# ValueError: invalid literal for int() with base 10: 'a'
# finally...
# END

# 可以在except语句块后面加一个else，当没有错误发生时，会自动执行else语句
try:
	print 'try...'
	r = 10 / int('a')
	print 'result:', r
except ValueError, e:
	print 'ValueError:', e
except ZeroDivisionError, e:
	print 'ZeroDivisionError:', e
else:
	print 'There is no error!'
finally:
	print 'finally...'
print 'END'
# result:
# try...
# ValueError: invalid literal for int() with base 10: 'a'
# finally...
# END

# Python 与java类似，所有的错误都继承自 BaseException, 所以在使用 except时需要注意的是，它不但捕获该类型的错误，还把其子类也“一网打尽”
try:
	dosomething = 0 
except StandardError, e:
	print 'StandardError:', e
except ValueError, e:
	print 'ValueError', e
# 第二个 except永远也捕获不到异常，因为 ValueError是 StandardError的子类。
# 常见的错误类型和继承关系：https://docs.python.org/2/library/exceptions.html#exception-hierarchy


# 使用 try...except还有一个好处：可以跨越多层调用，不许要在每个出错的地方去捕获错误，只要在合适的层次捕获错误就可以了

# Python 内置的logging模块可以非常容易地记录错误信息
import logging

def foo(s):
	return 10 / int(s)

def bar(s):
	return foo(s) * 2

def main():
	try:
		bar('0')
	except StandardError, e:
		logging.exception(e)

main()

print 'END'
# result:
# ERROR:root:integer division or modulo by zero
# Traceback (most recent call last):
#   File "/home/yrh/桌面/ex_except.py", line 99, in main
#     bar('0')
#   File "/home/yrh/桌面/ex_except.py", line 95, in bar
#     return foo(s) * 2
#   File "/home/yrh/桌面/ex_except.py", line 92, in foo
#     return 10 / int(s)
# ZeroDivisionError: integer division or modulo by zero
# END
# 通过配置，logging还可以把错误记录到日志文件里，方便事后排查


# 抛出错误
class FooError(StandardError):
	pass

def foo(s):
	n = int(s)
	if n == 0:
		raise FooError('invalid value: %s' % s)
	return 10 / n

print foo('1')
foo('0')
# result:
# 10
# Traceback (most recent call last):
#   File "/home/yrh/桌面/ex_except.py", line 131, in <module>
#     foo('0')
#   File "/home/yrh/桌面/ex_except.py", line 127, in foo
#     raise FooError('invalid value: %s' % s)
# __main__.FooError: invalid value: 0
# 并且程序终止了

# raise 语句如果不带参数，则将错误原样抛出
# 不仅如此，还可以将错误转换成另一个类型
try:
	10 / 0
except ZeroDivisionError:
	raise ValueError('input error!')












































