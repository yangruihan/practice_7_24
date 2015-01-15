# -*- coding: utf-8 -*-
# 
# filename:'ex_import_module.py'
#

import Image

im = Image.open('background4.jpg')

print im.format
print im.size
print im.mode
# result:
# JPEG
# (2560, 1600)
# RGB

import sys
print sys.path