# !/usr/bin/env python
# -*- coding:utf-8 -*-
#
# filename:'ex_module.py'
#

# 在Python中一个.py文件就是一个模块(Module)
# 为了避免模块冲突，用包(Package)来存放模块

' a test module '

__author__ = 'Yang.R.H'

import sys

def test():
	args = sys.argv
	if len(args) == 1:
		print 'Hello, world!'
	elif len(args) == 2:
		print 'Hello, %s!' % args[1]
	else:
		print 'Too many arguments!'

if __name__ == '__main__':
	test()

# import ... as xxx
# import cStringIO as StringIO
# 即用StringIO作为cStringIO的别名

# 私有函数和私有变量在Python中是以'_'前缀来实现的
# 不过，仅仅是不应该调用这个函数或变量，而不是不能
# 因为在Python中没有一种方法可以完全限制私有变量和函数

def _private_1(name):
	return 'Hello, %s' % name

def _private_2(name):
	return 'Hi, %s' % name

def greeting(name):
	if len(name) > 3:
		return _private_1(name)
	else:
		return _private_2(name)

print greeting('Yrh')
# result:Hi, Yrh




