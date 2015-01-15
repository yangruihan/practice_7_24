# -- coding: utf-8 --
#
# filename:'ex_use__property'
#

class Student(object):

	def get_score(self):
		return self._score

	def set_score(self, value):
		if not isinstance(value, int):
			raise ValueError('score must be an integer!')
		if value < 0 or value > 100:
			raise ValueError('score must between 0 ~ 100!')
		self._score = value

s = Student()
s.set_score(60)
print s.get_score()
# result: 60

# s.set_score(1000) # 报错提示
# Traceback (most recent call last):
#   File "/Users/yangruihan/Desktop/GitHub/practice_7_24/PythonPractice/ex_use__property.py", line 22, in <module>
#     s.set_score(1000)
#   File "/Users/yangruihan/Desktop/GitHub/practice_7_24/PythonPractice/ex_use__property.py", line 15, in set_score
#     raise ValueError('score must between 0 ~ 100!')
# ValueError: score must between 0 ~ 100!

# 使用@property可以将一个方法编程属性调用方式
class Student2(object):

	@property
	def score(self):
	    return self._score

	@score.setter
	def score(self, value):
		if not isinstance(value, int):
			raise ValueError('score must be an integer!')
		if value < 0 or value > 100:
			raise ValueError('score must between 0 ~ 100!')
		self._score = value

	# 如果之定义getter(@property)方法，不定义setter方法
	# 则该属性变成只读属性
	@property
	def age(self):
	    return self._age
	
s2 = Student2()
s2.score = 60
print s2.score
# resul: 60

# s2.score = 1000 错误同上














