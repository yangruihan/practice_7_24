# -- coding:utf-8 --
#
# filename:'ex_map_reduce.py'

# ----------map/reduce----------

print map(power2 ,L)
# result:[1, 4, 9, 16, 25, 36, 49, 64, 81, 0]

# reduce:把一个函数作用在一个序列[x1, x2, x3...]上，这个函数
# 必须接收两个参数，reduce把结果继续和序列的下一个元素做累计计算
# 其效果为：reduce(f ,[x1, x2, x3, x4]) = f(f(f(x1, x2), x3), x4)
# for example 将一个字符串转化成一个整数

def f1(x, y):
	return x * 10 + y

def char2num(s):
	return {'0':0, '1':1, '2':2, '3':3, '4':4,
	'5':5, '6':6, '7':7, '8':8, '9':9}[s]

print reduce(f1, map(char2num, '9236')) 
# result:9236