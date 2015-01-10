# -- coding: utf-8 --
#
#filename:'ex11.py'
#

print "How old are you?",
age = raw_input()

print "How tall are you?",
height = raw_input()

print "How much do you weight?",
weight = raw_input()

print "So, you're %r old, %r tall, %r heavy." % (age, height, weight)

# input 和 raw_input 的区别：
# 这两个函数均能接收 字符串 ，但 raw_input() 直接读取控制台的输入（任何类型的输入它都可以接收）。而对于 input() ，它希望能够读取一个合法的 python 表达式，即你输入字符串的时候必须使用引号将它括起来，否则它会引发一个 SyntaxError 。
# raw_input() 将所有输入作为字符串看待，返回字符串类型。而 input() 在对待纯数字输入时具有自己的特性，它返回所输入的数字的类型（ int, float ）；同时在例子 1 知道，input() 可接受合法的 python 表达式，举例：input( 1 + 3 ) 会返回 int 型的 4 

color = raw_input("What color do you like ?")

print "This wonderful, the color you like is %r" % color
