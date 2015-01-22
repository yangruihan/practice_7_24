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
# result: 49

# 我们定义了一个共享变量balance，初始值为0，并且启动两个线程，
# 先存后取，理论上结果应该为0，但是，由于线程的调度是由操作系统
# 决定的，当t1、t2交替执行时，只要循环次数足够多，balance的结
# 果就不一定是0了。原因是因为高级语言的一条语句在CPU执行时是若
# 干条语句，即使一个简单的计算：
# balance = balance + n
# 也分两步：
# 1.计算balance + n，存入临时变量中
# 2.将临时变量的值赋给balance
# 也就是可以看成：
# x = balance + n
# balance = x
# 由于x是局部变量，两个线程各自都有自己的x，当代码正常执行时：
#
# 初始值 balance = 0
#
# t1: x1 = balance + 5 # x1 = 0 + 5 = 5
# t1: balance = x1     # balance = 5
# t1: x1 = balance - 5 # x1 = 5 - 5 = 0
# t1: balance = x1     # balance = 0
#
# t2: x2 = balance + 8 # x2 = 0 + 8 = 8
# t2: balance = x2     # balance = 8
# t2: x2 = balance - 8 # x2 = 8 - 8 = 0
# t2: balance = x2     # balance = 0
#
# 结果 balance = 0
#
# 但是t1和t2是交替运行的，如果操作系统以下面的顺序执行t1、t2：
#
# 初始值 balance = 0
# 
# t1: x1 = balance + 5  # x1 = 0 + 5 = 5
# 
# t2: x2 = balance + 8  # x2 = 0 + 8 = 8
# t2: balance = x2      # balance = 8
# 
# t1: balance = x1      # balance = 5
# t1: x1 = balance - 5  # x1 = 5 - 5 = 0
# t1: balance = x1      # balance = 0
# 
# t2: x2 = balance - 5  # x2 = 0 - 5 = -5
# t2: balance = x2      # balance = -5
# 
# 结果 balance = -5
# 究其原因，是因为修改balance需要多条语句，而执行这几条语句时，
# 线程可能中断，从而导致多个线程把同一个对象的内容改乱了。
#
# 两个线程同时一存一取，就可能导致余额不对，你肯定不希望你的银行
# 存款莫名其妙地变成了负数，所以，我们必须确保一个线程在修改balance
# 的时候，别的线程一定不能改。

# 如果我们要确保balance计算正确，就要给change_it()上一把锁，
# 当某个线程开始执行change_it()时，我们说，该线程因为获得了锁，
# 因此其他线程不能同时执行change_it()，只能等待，直到锁被释放后，
# 获得该锁以后才能改。由于锁只有一个，无论多少线程，同一时刻最多只
# 有一个线程持有该锁，所以，不会造成修改的冲突。创建一个锁就是通过
# threading.Lock()来实现：

balance = 0
lock = threading.Lock()
	
def run_thread2(n):
	for i in range(100000):
		lock.acquire()
		try:
			change_it(n)
		finally:
			# 改完了一定要释放锁:
			lock.release()

t1 = threading.Thread(target = run_thread2, args = (5, ))
t2 = threading.Thread(target = run_thread2, args = (8, ))

t1.start()
t2.start()
t1.join()
t2.join()

print balance
# result: 0




















































