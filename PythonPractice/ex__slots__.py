# -- coding: utf-8 --
# 
# filename:'ex__slots__.py'
#

# 当我们定义了一个class，创建了一个class的实例后，
# 我们可以给该实例绑定任何属性和方法，这就是动态语言的灵活性。

class Student(object):
	pass

s = Student()
# 动态给实例绑定一个属性
s.name = 'Yang.R.H'
print s.name
# result: Yang.R.H

a = Student()
print hasattr(a, 'name')
# result: False
# 说明给一个实例动态绑定一个属性不会影响到其他属性

# 动态绑定一个方法
def set_age(self, age):
	self.age = age

from types import MethodType

s.set_age = MethodType(set_age, s, Student) # 给实例绑定一个方法
s.set_age(25) # 调用实例方法
print s.age 
# result: 25

s2 = Student()
# s2.set_age(24) # 会报错
# 说明给一个实例动态绑定一个方法，对另一个实例不起作用

# 为了给所有实例都绑定方法，可以给Class绑定方法：
def set_score(self, score):
	self.score = score

Student.set_score = MethodType(set_score, None, Student)

s.set_score(100)
s2.set_score(78)

print s.score
print s2.score
# result:
# 100
# 78

# 如果我们想限制Class的属性，则需要在定义类的时候
# 定义一个特殊的变量：__slots__

class Teacher(object):

	__slots__ = ('name', 'major') # 用tuple定义允许绑定的变量名

t = Teacher()

t.name = 'Yuan'
t.major = 'Data structure'
# t.age = 40 # 会报错

# 注意，__slots__仅对当前类有用，对其的子类是没有作用的
# 除非在子类中也定义__slots__，这样，子类允许定义的属性
# 就是自身的__slots__加上父类的__slots__









 







