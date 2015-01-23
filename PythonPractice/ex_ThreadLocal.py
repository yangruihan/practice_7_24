#!/usr/bin/env python
# -*- coding: utf-8 -*-
# filename:'ex_ThreadLocal'
#

import threading

# 创建全局ThreadLocal对象:
local_school = threading.local()

def process_student():
	print 'Hello, %s (in %s)' % (local_school.student, threading.current_thread().name)
	print 

def process_thread(name):
	# 绑定ThreadLocal的student:
	local_school.student = name
	process_student()

t1 = threading.Thread(target = process_thread, args = ('Allen', ), name = 'Thread-A')
t2 = threading.Thread(target = process_thread, args = ('Bob', ), name = 'Thread-B')

t1.start()
t2.start()
t1.join()
t2.join()

# ThreadLocal最常用的地方就是为每个线程绑定一个数据库连接，
# HTTP请求，用户身份信息等，这样一个线程的所有调用到的处理函
# 数都可以非常方便地访问这些资源。


