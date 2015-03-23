****
#Lisp学习笔记（一）
- author: Y.R.H
- time: 2015-3-10
<!--more-->

****

#Lisp定义函数的形式
~~~~~lisp
(defun name varlist &rest body)
~~~~~

****

#Emacs快捷键
- C-x C-f 创建一个新文件
- C-c C-q 匹配当前所有的开括号
- C-c C-c 编译 Lsip
- C-c C-z | C-x b 切换到 REPL
- C-x C-s 保存

**提示：在REPL中按','并输入 quit | sayoonara 回车，这将退出Lisp并且关闭所有 SLIME 创建的缓冲区，包括REPL缓冲区。**
