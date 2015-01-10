# -- coding: utf-8 --
#
#filename:'ex5.py'
#

my_name = 'Yang R.H.'
my_age = 20
my_weight = 67 # kg
my_height = 183 # cm
my_eyes = 'Black'
my_teeth = 'White'
my_hair = 'Black'

print "Let's talk about %s." % my_name
print "He's %d kg weight" % my_weight
print "He's %d cm height" % my_height
print "Actually that's not too high."
print "He's got %s eyes and %s hair." % (my_eyes, my_hair)
print "His teeth are usually %s depending on the coffee." % my_teeth

# this line is tricky, try to get it exactly right
print "If I add %d, %d, and %d I get %d." % (my_age, my_height, my_weight, my_age + my_height + my_weight)

print "Whatever will be printed %r" % "12123123"
print "Whatever will be printed %r" % 123123123

#%c				转换成字符（ASCII 码值，或者长度为一的字符串）
#%r				优先用repr()函数进行字符串转换（Python2.0新增）
#%s				优先用str()函数进行字符串转换
#%d / %i		转成有符号十进制数
#%u				转成无符号十进制数
#%o				转成无符号八进制数
#%x / %X		(Unsigned)转成无符号十六进制数（x / X 代表转换后的十六进制字符的大小写）
#%e / %E		转成科学计数法（e / E控制输出e / E）
#%f / %F		转成浮点数（小数部分自然截断）
#%g / %G		%e和%f / %E和%F 的简写
#%%				输出%

# for example
charA = 65
charB = 66
print("ASCII码65代表：%c" % charA)
print("ASCII码66代表：%c" % charB)
Num1 = 0xEF3
Num2 = 0xAB03
print('转换成十进制分别为：%u和%u' % (Num1, Num2))
Num3 = 1200000
print('转换成科学计数法为：%e' % Num3)

#	*			定义宽度或者小数点精度
#	-			用做左对齐
#	+			在正数前面显示加号(+)
#	<sp>		在正数前面显示空格
#	#			在八进制数前面显示零(0)，在十六进制前面显示“0x”或者“0X”（取决于用的是“x”还是“X”）
#	0			显示的数字前面填充“0”而不是默认的空格
#	m.n			m 是显示的最小总宽度，n 是小数点后的位数（如果可用的话）

# for example
Num1 = 108
print("%#X" % Num1)
Num2 = 234.567890
print("%.2f" % Num2)