# -*- coding: utf-8 -*-
#
# filename:'ex_os'
#

import os

# 输出操作系统的名称
print os.name 	
# 如果是posix，说明系统是Linux、Unix或Mac OS X
# 如果是nt，说明系统是Windows
print '------------------------'

# 输出详细的系统信息（Windows上不提供）
print os.uname() 
print '------------------------'

# 环境变量
print os.environ
print '------------------------'

# 要获取某个环境变量的值
print os.getenv('PATH')
print '------------------------'

# 操作文件和目录的函数一部分放在os模块中，一部分放在os.path模块中

# 查看当前目录的绝对路径
print os.path.abspath('.')
print '------------------------'

# 再某个目录下创建一个新目录
# 首先把新目录的完整路径表示出来
new_dir_path = os.path.join('/home/yrh/桌面/Python_Code', 'testdir')
print new_dir_path
os.mkdir(new_dir_path) # 创建一个目录
os.rmdir(new_dir_path) # 删除一个目录

# 提示：在把两个路径合并成一个的时候，不要直接拼字符串，而是要通过os.path.join()函数，
# 这样可以保证在不同的操作系统中，分隔符对应，在Linux/Unix/Mac下，分隔符是'/'，在
# Windows中分隔符是'\'
# 同样的道理在拆分路径时，也不要直接去拆分字符串，而是要通过os.path.split()函数，
# 拆分后为（前面部分+最后级别的目录或文件名）
s = os.path.split(new_dir_path)
print s
# result: ('/home/yrh/\xe6\xa1\x8c\xe9\x9d\xa2/Python_Code', 'testdir')

# 文件操作

# 对文件重命名
os.rename('os_test.txt', 'os_test.py')

# 删除文件
os.remove('os_test.py')

# os模块中没有复制文件的函数，原因是复制文件并非操作系统提供的系统调用
# 在shutil模块中提供了copyfile()函数

# 利用Python特性来过滤文件
# 列出当前目录下的所有目录
list1 = [x for x in os.listdir('.') if os.path.isdir(x)]
print list1

# 显示出所有的.py文件
list2 = [x for x in os.listdir('.') if os.path.isfile(x) and os.path.splitext(x)[1] == '.py']
print list2




















