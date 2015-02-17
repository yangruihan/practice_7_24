# -- coding:utf-8 --

import json
import re
import urllib
import sys

reload(sys)
sys.setdefaultencoding('gb18030')

# for i in range(946649, 946649):#数字代表京东商品编号
i = 1069497
URL = 'http://item.jd.com/%s.html' % (i)
page = urllib.urlopen(URL).read()
idx = page.find(u'clstag="shangpin|keycount|product|mbNav-3">洗衣机')
print idx
if (idx >= 0):  # 说明分类是洗衣机
    idx_name = page.find(u'name: ')
    name =


