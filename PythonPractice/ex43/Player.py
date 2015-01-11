#
# -- coding: utf-8 --
#

class Player(object):
    """
    玩家类
    healthy 			生命值
    location 			位置
    """

    def __init__(self, healthy, location):
        self.healthy = healthy
        self.location = location

    def death(self, why):
        print why, "Please try again!"

    def is_dead(self):
        if self.healthy == 0:
            return True
        else:
            return False