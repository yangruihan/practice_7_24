# -- coding: utf-8 --
#
# filename:'ex_use__future__.py'
#

# Python提供了__future__模块，把下一个新版本的特性
# 导入到当前版本，于是我们就可以在当前版本中测试一些新版本的特性。

# still running on Python 2.7

from __future__ import unicode_literals
from __future__ import division

print '\'xxx\' is unicode?', isinstance('xxx', unicode)
print 'u\'xxx\' is unicode?', isinstance(u'xxx', unicode)
print '\'xxx\' is str?', isinstance('xxx', str)
print 'b\'xxx\' is str?', isinstance(b'xxx', str)
# result:
# 'xxx' is unicode? True
# u'xxx' is unicode? True
# 'xxx' is str? False
# b'xxx' is str? True

print '10 / 3 =', 10 / 3
print '10.0 / 3 =', 10.0 / 3
print '10 // 3 =', 10 // 3
# result:
# 10 / 3 = 3.33333333333
# 10.0 / 3 = 3.33333333333
# 10 // 3 = 3

