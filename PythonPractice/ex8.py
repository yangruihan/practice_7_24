# -- coding: utf-8 --
#
#filename:'ex8.py'
#

formatter = "%r %r %r %r"

print formatter % (1, 2, 3, 4)
print formatter % ("one", "two", "three", "four")
print formatter % (True, False, False, True)
print formatter % (formatter, formatter, formatter, formatter)
print formatter % (
		"I had this thing.",
		"That you could type up right.",
		"But it didn't sing.",
		"So I said goodnight."
	)

# 如果一个string中包含的有'，那么打印出的结果就是用""引起来的，否则就是用''引起来。