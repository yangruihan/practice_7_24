# -- coding:utf-8 --
#
# filename:'ex_map_reduce_test1.py'
#

def f(s):
	return s[0: 1].upper() + s[1: ].lower()

def change(l):
	return map(f, l)

test1_input = ['adam', 'LISA', 'barT']
test2_input = ['abHKqkqwe', 'qwdlLKNL' ,'QqwkLmma']

print change(test1_input)
print change(test2_input)