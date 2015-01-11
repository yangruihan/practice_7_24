#
# -- coding: utf-8 --
#


from sys import exit
from random import randint
from Player import *


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

    def __init__(self, row=2, col=2):
        self.room_number = row * col
        self.room_row = row
        self.room_col = col

        self.monster_number = []
        for i in range(self.room_number):
            j = randint(1, 9)  # 用来表示房间怪物的数量
            self.monster_number.append(j)

        self.healthy_potion_number = []
        for i in range(self.room_number):
            j = randint(1, 9)  # 用来表示房间击败怪物后获得的药水数量
            self.healthy_potion_number.append(j)

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

    def get_healthy_potion_number(self, x, y):
        """获取治疗药水的数量"""
        return self.healthy_potion_number[y * self.room_col + x]

    def is_win(self):
        """是否到达出口"""
        return self.new_player.location == self.exit_location

    def play(self):
        print "\n"
        print "Welcome to the Test World!"
        print "There are some monster in different room!"
        print "You should do everything to live!"
        print "\n"
        print "You are at (%d, %d) now" % (self.new_player.location[0], self.new_player.location[1])
        print "The exit is at (%d, %d)" % (self.exit_location[0], self.exit_location[1])
        print "\n"
        print "tips:You can use the 'w', 's', 'a' and 'd' to move your role"
        print "And now your health is %d\n" % self.new_player.healthy
        print "GOOD LUCK!"
        print "\n\n-----------------------------------------------------------"

        while not self.is_win():
            print "Now you should make a choice"
            print "tips:You can use the 'w', 's', 'a' and 'd' to move your role"

            c = raw_input("> ")

            while self.move_player(c) != "OK":
                print "I can't move to that location, please try another way!"
                print "tips:You can use the 'w', 's', 'a' and 'd' to move your role"
                c = raw_input("> ")

            if self.is_win():
                print "\n-----------------------------------------------------------"
                print "Congradulations! You have passed the test! You are genius!"
                print "\n-----------------------------------------------------------"
                exit(0);
            else:
                monster_number = self.get_monster_number(self.new_player.location[0],
                                                         self.new_player.location[1])
                print "\nNow you are (%d, %d)!" % (self.new_player.location[0], self.new_player.location[1])
                print "But don't be happy!"
                print "There are %d monster(s)" % monster_number
                print "You have 2 methods to choose:"
                print "**"
                print "1. fight, your health will subtract the same values as the number of monsters, "
                print "    but if you kill the monsters, you will get some healthy potions."
                print "2. subtract half of your health, and you can go on."
                print "**"
                print "Please make your choice",

                player_choice = raw_input(" > ")

                while player_choice != "1" and player_choice != "2":
                    print "I have no idea about that, please type '1'/'2'",
                    player_choice = raw_input(" > ")
                    continue

                if player_choice == "1":
                    self.new_player.healthy -= monster_number
                    # 如果生命值为0
                    if self.new_player.is_dead():
                        self.new_player.death("\nSorry, Game Over!")
                    else:
                        potion_number = self.get_healthy_potion_number(
                            self.new_player.location[0],
                            self.new_player.location[1])
                        print "\nYou are a lucky guy!"
                        print "You get %d healthy potion!\n" % potion_number / 2
                        self.new_player.healthy += (potion_number / 2)
                        if self.new_player.healthy > self.room_number:
                            self.new_player.healthy = self.room_number
                        else:
                            pass
                elif player_choice == "2":
                    self.new_player.healthy /= 2
                    if self.new_player.is_dead():
                        self.new_player.death("\nSorry, Game Over!")
                        exit(0)
                    else:
                        pass

                print "Now your health is %d" % self.new_player.healthy
                print "You are at (%d, %d) now" % (self.new_player.location[0], self.new_player.location[1])
                print "The exit is at (%d, %d)" % (self.exit_location[0], self.exit_location[1])
                print "\n-----------------------------------------------------------"

    def move_player(self, c):
        print c
        """
        移动游戏角色
        """
        if c == 's':
            if self.new_player.location[1] - 1 >= 0:
                self.new_player.location[1] -= 1
                return "OK"
            else:
                return "ERROR"
        elif c == 'w':
            if self.new_player.location[1] + 1 < self.room_row:
                self.new_player.location[1] += 1
                return "OK"
            else:
                return "ERROR"
        elif c == 'a':
            if self.new_player.location[0] - 1 >= 0:
                self.new_player.location[0] -= 1
                return "OK"
            else:
                return "ERROR"
        elif c == 'd':
            if self.new_player.location[0] + 1 < self.room_col:
                self.new_player.location[0] += 1
                return "OK"
            else:
                return "ERROR"
        else:
            return "ERROR"