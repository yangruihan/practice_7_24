# -- coding: utf-8 --
#
# filename:'ex_class&instance.py'
#

class Student(object):
	
	def __init__(self, name, score):
		self.name = name
		self.score = score

	def print_score(self):
		print self.name, ":", self.score

bart = Student('Yrh', 100)

print bart.name
print bart.score
# result:
# Yrh
# 100

bart.print_score()
# result:
# Yrh : 100

# 在Python中，实例的变量名如果以__开头，
# 就变成了一个私有变量（private），只有内部可以访问，外部不能访问

class Teacher(object):

	def __init__(self, name, major):
		self.__name = name
		self.__major = major

	def print_major(self):
		print self.__name, ":", self.__major

t = Teacher('YuanSir', 'Data structure')
t.print_major()
# result: YuanSir : Data structure

t.__name = 'Yuan'
t.print_major()
# print t.__major # 会报错
# result: YuanSir : Data structure

# 形如：__xxx__ 这样的变量在类中是可以直接访问的

		
