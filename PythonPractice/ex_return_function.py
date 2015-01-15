# -- coding:utf-8 --
#
# filename:'ex_return_function.py'

# ----------return_function----------

# 高阶函数不仅可以接受函数作为参数，还可以将函数作为结果值返回

def lazy_sum(*args):
	def sum():
		ax = 0
		for n in args:
			ax = ax + n
		return ax
	return sum

f1 = lazy_sum(1, 2, 3, 4, 5)
f2 = lazy_sum(1, 2, 3, 4, 5)

print f1
print f2
print f1 == f2
# result:
# <function sum at 0x105ee6500>
# <function sum at 0x105ee6578>
# False

print f1()
print f2()
print f1() == f2()
# result:
# 15
# 15
# True

f1 = lazy_sum(1, 2, 3, 4, 5)
f1 = lazy_sum(2, 4, 6, 7, 8)
print f1()
# result:27

# 在这个例子中，我们在函数lazy_sum中又定义了函数sum，并且，
# 内部函数sum可以引用外部函数lazy_sum的参数和局部变量，当
# lazy_sum返回函数sum时，相关参数和变量都保存在返回的函数
# 中，这种称为“闭包（Closure）”的程序结构拥有极大的威力。

# 返回的函数并没有立刻执行，而是直到调用了f()才执行。
def count():
	fs = []
	for i in range(1, 4):
		def f():
			return i * i
		fs.append(f)
	return fs

f1, f2, f3 = count()

print f1()
print f2()
print f3()
# result:
# 9
# 9
# 9

def count():
	fs = []
	for i in range(1, 4):
		def f(j):
			def g():
				return j * j
			return g
		fs.append(f(i))
	return fs

f1, f2, f3 = count()

print f1()
print f2()
print f3()
# result:
# 1
# 4
# 9














