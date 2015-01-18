# -*- coding: utf-8 -*-
#
# filename:'ex_unit_test'
#

# 单元测试是用来对一个模块、一个函数或者一个类来进行正确性检验的测试工作

class Dict(dict):
	
	def __init__(self, **kw):
		super(Dict, self).__init__(**kw)
		
	def __getattr__(self, key):
		try:
			return self[key]
		except KeyError:
			raise AttributeError(r"'Dict' object has no attribute '%s'" % key)
			
	def __setattr__(self, key, value):
		self[key] = value
		
import unittest

class TestDict(unittest.TestCase):

	def test_init(self):
		d = Dict(a=1, b='test')
		self.assertEquals(d.a, 1)
		self.assertEquals(d.b, 'test')
		self.assertTrue(isinstance(d, dict))

	def test_key(self):
		d = Dict()
		d['key'] = 'value'
		self.assertEquals(d.key, 'value')

	def test_attr(self):
		d = Dict()
		d.key = 'value'
		self.assertTrue('key' in d)
		self.assertEquals(d['key'], 'value')

	def test_keyerror(self):
		d = Dict()
		with self.assertRaises(KeyError):
			value = d['empty']

	def test_attrerror(self):
		d = Dict()
		with self.assertRaises(AttributeError):
			value = d.empty

if __name__ == '__main__':
	unittest.main()
# python -m unittest ex_unit_test 
# result:
# .....
# ----------------------------------------------------------------------
# Ran 5 tests in 0.001s
# 
# OK

# 可以在单元测试中编写两个特殊的setUp()和tearDown()方法。
# 这两个方法会分别在每调用一个测试方法的前后分别被执行。
# setUp()和tearDown()方法有什么用呢？设想你的测试需要启动一个数据库，
# 这时，就可以在setUp()方法中连接数据库，在tearDown()方法中关闭数据库，
# 这样，不必在每个测试方法中重复相同的代码

class TestDict2(unittest.TestCase):

    def setUp(self):
        print 'setUp...'

    def tearDown(self):
        print 'tearDown...'









