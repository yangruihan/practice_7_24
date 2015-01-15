# -- coding: utf-8 --
#
# filename:'ex_inherit.py'
#

class Animal(object):

	def __init__(self):
		pass

	def run(self):
		print 'Animal is running...'

class Dog(Animal):
	
	def run(self):
		print 'Dog is running'

	def eat(self):
		print 'Dog is eating'

class Cat(Animal):
	
	def run(self):
		print 'Cat is running'

	def eat(self):
		print 'Cat is eating'

dog = Dog()
dog.run()

cat = Cat()
cat.run()
# result:
# Dog is running
# Cat is running

# 判断某个实例或变量是否为某个类型，可以用ininstance()
print isinstance(dog, Dog)
print isinstance(dog, Animal)
# result:
# True
# True

a = Animal()
print isinstance(a, Animal)
print isinstance(a, Dog)
# result:
# True
# False

def run_twice(animal):
	animal.run()
	animal.run()

run_twice(a)
run_twice(dog)
run_twice(cat)
# Animal is running...
# Animal is running...
# Dog is running
# Dog is running
# Cat is running
# Cat is running







