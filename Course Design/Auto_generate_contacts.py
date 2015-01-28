#!/usr/bin/env python
# -*-coding: utf-8 -*-
#
# filename:'Contacts_Generator'
#

__author__ = 'Yang R.H.'

import random
import string

def Generate_contacts():
	group = str(random.randint(1, 8))
	name = "".join(random.sample('abcdefghijklmnopqrstuvwxyz',random.randint(3, 6)))
	if group != '6':
		gender = random.choice(['男', '女'])
		birth = str(random.randint(1800, 2015)) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1,31))
	else:
		gender = '/'
		birth = '/'
	phone_num1 = str(random.randint(10000000000,19999999999))
	phone_num2 = str(random.choice([random.randint(10000000000,19999999999), '/']))
	QQ_num = str(random.choice([random.randint(10000000,9999999999), '/']))
	location = random.choice(['北京', '天津', '上海', '重庆', '新疆', '西藏', '宁夏', '内蒙古', '广西', '黑龙江', '哈尔滨', '大庆', '齐齐哈尔', '佳木斯' , '鸡西', '鹤岗' , '双鸭山', '牡丹江', '伊春', '七台河', '黑河' ,'绥化', '加格达奇', '吉林', '长春', '四平', '辽源', '通化', '白山', '松原', '白城',  '辽宁', '沈阳', '大连', '金州', '鞍山' ,'抚顺', '铁岭', '朝阳', '葫芦岛', '唐山', '邯郸', '秦皇岛', '保定', '张家口', '淄博' ,'枣庄', '东营', '青岛', '常州', '无锡', '苏州', '淮南', '亳州', '杭州', '嘉兴' ,'湖州', '福州' ,'厦门','深圳','汕头','海口','三亚','昆明','丽江','成都市','自贡市','攀枝花市','长沙','株洲','武汉','襄樊','宜昌','郑州','洛阳','开封','太原','大同','兰州','西宁','南昌','九江','台湾','香港','澳门'])
	return group + " " + name + " " + gender + " " + birth + " " + phone_num1 + " " + phone_num2 + " " + QQ_num + " " + location + "\n"

with open('Contacts_100.txt', 'w') as f:
	for i in range(100):
		f.write(Generate_contacts())








