# -*- coding: utf-8 -*-
#
# filename:'ex_debug'
# 

# 方法一：print出可能出问题的变量
# 弊端：如果程序量很大，程序中会出现大量的 print语句，在调试完后要将其全部删除


# 方法二：凡是用 print来辅助查看的地方，都可以用断言(assert)来替代
#def foo(s):
	#n = int(s)
	#assert n != 0, 'n is zero!'
	#return 10 / n

#def main():
#	foo('0')

#main()
# result:
# Traceback (most recent call last):
#   File "/home/yrh/桌面/ex_debug.py", line 18, in <module>
#     main()
#   File "/home/yrh/桌面/ex_debug.py", line 16, in main
#     foo('0')
#   File "/home/yrh/桌面/ex_debug.py", line 12, in foo
#     assert n != 0, 'n is zero!'
# AssertionError: n is zero!
# assert的意思是，表达式n != 0应该是true，否则，后面的代码就会出错
# 如果断言失败，assert语句本身就会抛出 AssertionError的异常
# 启动 Python解释器可以用 -O参数来关闭assert
# for example: python -O xxx.py (大写O)


# 方法三：用 logging来替换 print
import logging
logging.basicConfig(level = logging.INFO)

s = '0'
n = int(s)
logging.info('n = %d' % n)
print 10 / n
# result:
# INFO:root:n = 0
# Traceback (most recent call last):
#   File "/home/yrh/桌面/ex_debug.py", line 42, in <module>
#     print 10 / n
# ZeroDivisionError: integer division or modulo by ze

# logging的好处: 它允许你指定记录信息的级别，有debug，info，
# warning，error等几个级别，当我们指定level=INFO时，
# logging.debug就不起作用了。同理，指定level=WARNING后，
# debug和info就不起作用了。

# logging的另一个好处是通过简单的配置，一条语句可以同时输出到
# 不同的地方，比如console和文件。


# 方法四：启动 Python调试器 pdb，让程序以单步方式运行，可以随时查看运行状态
# 具体步骤参看：http://www.liaoxuefeng.com/wiki/001374738125095c955c1e6d8bb493182103fac9270762a000/00138683229901532c40b749184441dbd428d2e0f8aa50e000




































