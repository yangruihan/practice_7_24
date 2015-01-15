# -- coding: utf-8 --
#
# filename:'ex_multiple_inheritance'
#

class Animal(object):
	pass

# 大类
class Mammal(Animal):
	pass

class Bird(Animal):
	pass

class RunnableMixin(object):

	def run(self):
		print "running..."

class FlyableMinxin(object):

	def fly(self):
		print "Flying..."

# 各种动物
class Dog(Mammal, RunnableMixin):
	pass

class Bat(Mammal, FlyableMinxin):
	pass

class Parrot(Bird, FlyableMinxin):
	pass

class Ostrich(Bird, RunnableMixin):
	pass














