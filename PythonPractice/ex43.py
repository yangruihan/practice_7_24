#
# -- coding: utf-8 --
#

from sys import exit
from random import randint

class Map(object):
	""" 
	游戏地图类
	room_number 			地图中房间的数量
	room_row				地图的行数
	room_col				地图的列数
	monster_number 			地图中每个房间的怪兽的数量
	healthy_potion_number	地图中每个房间的药水的数量
	player_location			玩家当前的位置
	exit_location			通关的位置 
	"""

	def __init__(self, row = 2, col = 2):
		self.room_number = row * col
		self.room_row = row
		self.room_col = col

		print "room_number :" ,self.room_number
		print "room_row :", self.room_row
		print "room_col :", self.room_col

		self.monster_number = []
		for i in range(self.room_number):
			j = randint(1, 9) # 用来表示房间怪物的数量
			self.monster_number.append(j)

		self.healthy_potion_number = []
		for i in range(self.room_number):
			j = randint(1, 9) # 用来表示房间击败怪物后获得的药水数量
			self.healthy_potion_number.append(j)
		
		print "monster_number :", self.monster_number
		print "healthy_potion_number :", self.healthy_potion_number

		# 新建一个玩家, 并生成他的出生地
		self.new_player = Player(self.room_number, [randint(0, 5), randint(0, 4)])

		# 通关的位置
		self.exit_location = [randint(0, 5), randint(0, 4)]

		# 避免玩家出生坐标和通关坐标相等
		while self.new_player.location == self.exit_location:
			self.exit_location = [randint(0, 5), randint(0, 4)]

	def get_monster_number(self, x, y):
		"""获取怪兽数量"""
		return self.monster_number[y * self.room_col + x]

	def is_win(self):
		"""是否到达出口"""
		return self.new_player.location == self.exit_location 


class Player(object):
	"""
	玩家类
	healthy 			生命值
	location 			位置
	"""
	def __init__(self, healthy, location):
		self.healthy = healthy
		self.location = location

		
		


game_map = Map(5, 6)
print game_map.get_monster_number(3, 0)
print game_map.is_win()


















