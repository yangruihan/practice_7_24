# -*- coding: utf-8 -*-
#
# filename:'ex_IO_stream'
#

# 在磁盘上读写文件的功能都是由操作系统提供的，现代操作系统不允许
# 普通的程序直接操作磁盘，所以，读写文件就是请求操作系统打开一个
# 文件对象（通常称为文件描述符），然后，通过操作系统提供的接口从
# 这个文件对象中读取数据（读文件），或者把数据写入这个文件对象（写文件）。


# 读文件
f = open('/home/yrh/桌面/Python_Code/test.txt', 'r')
print f.read()
f.close()
# result:
# Chinese:这是一个测试文档
# English:This is a test document.


# 由于文件读写时都有可能产生IOError，一旦出错，后面的f.close()就不会调用。
# 所以，为了保证无论是否出错都能正确地关闭文件，我们可以使用try ... finally来实现：
try:
	f1 = open('/home/yrh/桌面/Python_Code/test.txt', 'r')
	print f1.read()
finally:
	if f:
		f.close()
# result:
# Chinese:这是一个测试文档
# English:This is a test document.

# 但是每次都这么写实在太繁琐，所以，Python引入了with语句来自动帮我们调用close()方法：
with open('/home/yrh/桌面/Python_Code/test.txt', 'r') as f2:
	print f2.read()
# result:
# Chinese:这是一个测试文档
# English:This is a test document.

# 调用read()会一次性读取文件的全部内容，如果文件有10G，内存就爆了，
# 所以，要保险起见，可以反复调用read(size)方法，每次最多读取size
# 个字节的内容。另外，调用readline()可以每次读取一行内容，调用readlines()
# 一次读取所有内容并按行返回list。

with open('/home/yrh/桌面/Python_Code/test.txt', 'r') as f3:
	for line in f3.readlines():
		print(line.strip())	# 把末尾的'\n'删掉

# 像open()函数返回的这种有个read()方法的对象，在Python中统称为
# file-like Object。除了file外，还可以是内存的字节流，网络流，
# 自定义流等等。file-like Object不要求从特定类继承，只要写个read()方法就行。
# StringIO就是在内存中创建的file-like Object，常用作临时缓冲。

# 要想读取二进制文件，比如图片、视频等等，用'rb'模式打开文件即可
with open('/home/yrh/practice_7_24/PythonPractice/background4.jpg', 'rb') as f4:
	print f4.read()

# 要想读取非ASCII编码的文本文件，就必须以二进制模式打开，再解码。比如GBK编码的文件
# f5 = open('/home/yrh/桌面/Python_Code/测试.txt', 'rb')
# u = f5.read().decode('gbk')
# print u

# 如果每次都这么手动转换编码嫌麻烦,Python还提供了一个codecs模块帮我们在
# 读文件时自动转换编码，直接读出unicode
# import codecs
# with codecs.open('/Users/michael/gbk.txt', 'r', 'gbk') as f:
#     f.read() # u'\u6d4b\u8bd5'


# 写文件
# 写文件和读文件是一样的，唯一区别是调用open()函数时，
# 传入标识符'w'或者'wb'表示写文本文件或写二进制文件
f6 = open('/home/yrh/桌面/Python_Code/output_test.txt', 'w')
content = 'This is a test document, Hello World!'
f6.write(content)
f6.close()








































































