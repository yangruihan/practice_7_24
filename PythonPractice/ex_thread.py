#!/usr/bin/env python
# -*- coding: utf-8 -*-
# filename:'ex_thread'
#

# 对于多线程
# Python的标准库提供了两个模块：thread和 threading，thread是低级模块
# threading是高级模块，对 thread进行了封装，绝大多数情况下，我们只需要
# 使用 threading这个高级模块

# 启动一个线程就是把一个函数传入并创建一个 Thread实例，然后调用 start()开始执行

import time, threading

# 新线程执行的代码：
def loop():
	print 'thread %s is running...' % threading.current_thread().name
	n = 0
	
	while n < 5:
		n = n + 1
		print 'thread %s >>> %s' % (threading.current_thread().name, n)
		time.sleep(1)
	
	print 'thread %s ended.' % threading.current_thread().name
	
print 'thread %s is running...' % threading.current_thread().name
t = threading.Thread(target = loop, name = 'TempThread')
t.start()
t.join()
print 'thread %s ended.' % threading.current_thread().name
# result:
# thread MainThread is running...
# thread TempThread is running...
# thread TempThread >>> 1
# thread TempThread >>> 2
# thread TempThread >>> 3
# thread TempThread >>> 4
# thread TempThread >>> 5
# thread TempThread ended.
# thread MainThread ended.

# 多线程和多进程最大的不同在于，多进程中，同一个变量，各自有一份拷贝
# 存在于每个进程中，互不影响，而多线程中，所有变量都由所有线程共享，
# 任何一个变量都可以被任何一个线程修改，线程之间共享数据在于多个线程
# 同时更改一个变量，把内容改乱了

# 假设这是你的银行存款
balance = 0

def change_it(n):
	# 先存后取，结果应该为0
	global balance
	balance = balance + n
	balance = balance - n
	
def run_thread(n):
	for i in range(100000):
		change_it(n)

t1 = threading.Thread(target = run_thread, args = (5, ))
t2 = threading.Thread(target = run_thread, args = (8, ))

t1.start()
t2.start()
t1.join()
t2.join()

print balance

























































