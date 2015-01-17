# -*- coding: utf-8 -*-

import urllib2
import cookielib
import urllib
import string
import cStringIO
import Image
import re

cookie = cookielib.CookieJar()
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cookie))
opener.addheaders.append(('User-Agent', 'Mozilla/5.0 (Windows NT 5.1; rv:25.0) Gecko/20100101 Firefox/25.0'))

#opener.open(urllib2.Request('http://jwxt.wust.edu.cn/whkjdx/'))
opener.open(urllib2.Request('http://gsinfo.whu.edu.cn'))

#img_url = 'http://jwxt.wust.edu.cn/whkjdx/verifycode.servlet'
img_url = 'http://gsinfo.whu.edu.cn/servlet/GenImg'
user_id = raw_input("学号：")
pwd = raw_input("密码：")

res = opener.open(urllib2.Request(img_url))
tempIm = cStringIO.StringIO(res.read())
im = Image.open(tempIm)
im.show()
yzm = raw_input("验证码：")

postdata = 'USERNAME='+userid+'&PASSWORD='+pwd+'&useDogCode=&useDogCode=&RANDOMCODE='+yzm+'&x=38&y=1'
print postdata