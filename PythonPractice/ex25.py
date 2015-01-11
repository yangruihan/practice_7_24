# -- coding: utf-8 --
#
#filename:'ex25.py'
#

# 把一句话划分成若干个单词，存在一个list中
def break_words(stuff):
	"""This function will break up words for us."""
	words = stuff.split(' ')
	return words

# 将list中的元素排序（从小到大）
def sort_words(words):
	"""Sorts the words."""
	return sorted(words)

# 输出list中第一个元素，并删除
def print_first_word(words):
	"""Prints the first word after popping it off."""
	print words.pop(0)

# 输出list中最后一个元素，并删除
def print_last_word(words):
	"""Prints the last word after popping it off."""
	print words.pop(-1)

# 先将一句话分成若干个单词，然后将这些单词排序
def sort_sentence(sentence):
	"""Takes in a full sentence and returns the sorted words."""
	words = break_words(sentence)
	return sort_words(words)

# 打印第一个单词和最后一个单词
def print_first_and_last(sentence):
	"""Prints the first and last words of the sentence."""
	words = break_words(sentence)
	print_first_word(words)
	print_last_word(words)

# 打印第一个单词和最后一个单词（排好序后的list）
def print_first_and_last_sorted(sentence):
	"""Sorts the words then prints the first and last one."""
	words = sort_sentence(sentence)
	print_first_word(words)
	print_last_word(words)

print sort_words(break_words("hello world ni hao a"))
print_first_word(break_words("hello world ni hao a"))
print_last_word(break_words("hello world ni hao a"))
print_first_and_last('This is a good day!')
print sort_sentence("How beatiful you are!")
print_first_and_last_sorted("How beatiful you are!")

