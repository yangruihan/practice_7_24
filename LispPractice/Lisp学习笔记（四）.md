****
#Lisp学习笔记（四）
- author: Y.R.H
- time: 2015-3-13
<!--more-->

****

#变量
- Common Lisp 支持两种类型的变量：*词法 (lexical) 变量*和 *动态 (dynamic) 变量*。
- 这两种变量分别对应于其他语言中的 *局部变量*和 *全局变量*(大致相似)。
- *动态变量有时也称为 *特殊变量(special variable)*
- 变量的基础知识：Common Lisp 中的变量是一些可以保存值的具名位置。

##全局变量
- Common Lisp 提供了两种创建全局变量的方式：
\- 1 DEFVAR
\- 2 DEFPARAMETER
- 两种形式都接受**一个变量名**、**一个初始值**以及**一个可选的文档字符串**。
- 在被 **DEFVAR** 和 **DEFPARAMETER** 定义以后，该名字可用于任何位置来指向全局变量的当前绑定。
- 全局变量习惯上被命名为以 (\*)开始和结尾的名字。
- 如：
~~~lisp
(defvar *count* 0 "Count of widgets made so far.")
(defparameter *gap-tolerance* 0.001 "Tolerance to be allowed in widget gaps.")
~~~
- 区别：
\- **DEFPARAMETER** 总是将初始值赋给命名的变量，而DEFVAR只有当变量未定义时才这样做。
\- **DEFVAR** 形式也可以不带初始值来使用，从而在不给定其值的情况下定义一个全局变量。
\- 这样的变量称为 *未绑定的(unbound)*。
- 如果你特定想要重设由 DEFVAR 定义的变量，要么使用 SETF直接设置它，要么使用 MAKUNBOUND 先将其变成未绑定的，再重新求值 DEFVAR 形式。
- 在用 DEFVAR 和 DEFPARAMETER 定义一个变量之后，就可以从任何一个地方引用它。
- 如：
~~~lisp
(defun increment-widget-count () (incf *count*))
~~~
- 如果想要临时重定义全局变量(如：\*standard-output\*)，只需要将其重新绑定即可，比如可以用 LET ：
~~~lisp
(let ((*standard-output* *some-other-stream*))
    (stuff))
~~~
- 在任何由于调用 stuff 而运行的代码中，对 \*standard-output\* 的引用将使用由 LET 所创建的绑定，并且当 stuff 返回并且程序控制离开 LET 时，这个对 \*standard-output\* 的新绑定就随之消失，接下来对 \*standard-output\* 的引用将回到 LET 之前的绑定。(在任何给定时刻，最近建立的绑定会覆盖所有其他的绑定)
- 一个简单的例子：
~~~lisp
(defvar *x* 10)
(defun foo () (format t "x: ~d~%" *x*))

CL-USER> (foo)
x: 10
NIL

CL-USER> (let ((*x* 20)) (foo))
x: 20
NIL

CL-USER> (foo)
x: 10
NIL

(defun bar () 
    (foo)
    (let ((*x* 20)) (foo))
    (foo))

CL-USER> (bar)
x: 10
x: 20
x: 10
NIL
~~~

****

#常量
- 所有的常量都是全局的，并且使用 **DEFCONSTANT** 定义：
~~~lisp
(defconstant name initial-value-form [documentation-string])
~~~
- 与 **DEFVAR** 和 **DEFPARAMETER** 相似， **DEFCONSTANT** 在所使用的名字上产生了全局效果——从此该名字仅被用于指向常量，它不能被用作函数形参或是任何其他的绑定形式进行重绑定。
- **一般用 +constant-variable-name+ 的形式来表示常量**

****

#赋值
- 为绑定赋予新值需要使用 SETF 宏—— Common Lisp 的通用赋值操作符，其基本形式为：
~~~lisp
(setf place value)
~~~
- 如：
~~~lisp
(setf x 10)     ; 将值 10 赋给变量 x
~~~
- 为一个绑定赋予新值对该变量的任何其他绑定没有效果，并且它对赋值之前绑定上所保存的值也没有任何效果。

- SETF 也可用于依次对多个位置赋值。
- 如：
~~~lisp
(setf x 1)
(setf y 2)
; 可以写成：
(setf x 1 y 2)
~~~

- SETF 返回最近被赋予的值，因此也可以像下面的表达式那样嵌套调用 SETF ，将 x 和 y 赋予同一个随机值：
~~~lisp
(setf x (setf y (random 10)))
~~~

****

#广义赋值
~~~lisp
Simple variable:        (setf x 10)
Array:                  (setf (aref a 0) 10)
Hash table:             (setf (gethash 'key hash) 10)
Slot named 'field':     (setf (field o) 10)
~~~
- **AREF 是数组访问函数，GETHASH 做哈希表查找。**

#其他修改位置的方式：
~~~lisp
(incf x)                ≡           (setf x (+ x 1))
(decf x)                ≡           (setf x (- x 1))
(incf x 10)             ≡           (setf x (+ x 10))
~~~
- **类似 INCF 和 DECF 这种宏称为 修改宏(modify macro)**

##ROTATEF:
\- 在位置之间轮换他们的值：
~~~lisp
(rotatef a b)       ; 将交换两个变量的值并返回NIL
~~~
- **等价于(let ((tmp a)) (setf a b b tmp) nil)**

##SHIFTF:
\- 它将值向左侧移动而不轮换它们：
~~~lisp
(shiftf a b 10) ; 将 b 的值赋给 a ，将 10 赋值给 b
~~~
- **等价于(let ((tmp a)) (setf a b b 10) tmp)**

****
#宏：标准控制构造

##WHEN 和 UNLESS
- 特殊操作符 PROGN 可以按顺序执行任意数量的形式并返回最后一个形式的值。
~~~lisp
(if (spam-p current-message)
    (progn 
        (file-in-spam-folder current-message)
        (update-spam-database current-message)))
; 等价于：
(when (spam-p current-message)
    (file-in-spam-folder current-message)
    (update-spam-database current-message))
~~~
- 如果 WHEN 没有被内置到标准库中，可以像下面这样用一个宏来自己定义 WHEN：
~~~lisp
(defmacro when (condition &rest body)
    `(if ,condition (progn ,@body)))
~~~

- 与 WHEN 宏同一个系列的另一个宏是 UNLESS， 它取相反的条件，只有当条件为假时才求值其形式体，相当于：
~~~lisp
(defmacro unless (condition &rest body)
    `(if (not ,condition) (progn ,@body)))
~~~

##COND
- 用于表达多重分支条件的宏 COND，它的基本结构：
~~~lisp
(cond
    (test-1 form*)
        .
        .
        .
    (test-N form*))
- 主体中的每个元素都代表一个条件分支，并由一个列表所构成，列表中含有一个条件形式，以及零或多个当该分支被选择时将被求值的形式。
- 习惯上，那个用来表示 if/else-if 链中最后一个 else 子句的分支将被写成带有条件T。虽然任何非 NIL 的值都可以使用，但在阅读代码时， T 标记确实有用。
- 如：
~~~lisp
(cond (a (do-x))
    (b (do-y))
    (t (do-z)))
~~~

##AND、OR 和 NOT(布尔逻辑操作符)
- NOT：
\- 接受单一参数并对其真值取反，当参数为 NIL 时返回 T，否则返回 NIL。
- AND 和 OR 则是宏：
\- 实现了对任意数量子表达式的逻辑合取和析取操作，并被定义成宏以便支持“短路”特性。
- 如：
~~~lisp
(not nil)                       →       T
(not (= 1 1))                   →       NIL
(and (= 1 2) (= 3 3))           →       NIL
(or (= 1 2) (= 3 3))            →       T
~~~

##循环
###DOLIST：
- 在一个列表的元素上循环操作，使用一个依次持有列表中所有后继元素的变量来执行循环体。
- 形如：
~~~lisp
(dolist (var list-form) body-form*)
~~~
- 例如：
~~~lisp
CL-USER> (dolist (x '(1 2 3)) (print x))
1
2
3
NIL
~~~
- 在这种方式下，DOLIST 这种形式本身求值为 NIL
- 如果想在列表结束之前中断一个 DOLIST 循环，则可以使用 RETURN 
~~~lisp
CL-USER> (dolist (x '(1 2 3)) (print x) (if (evenp x)(return)))
1
2
NIL
~~~

###DOTIMES
- 用于循环计数的高级循环构造，形如：
~~~lisp
(dotimes (var count-form) body-form*)
~~~
- **其中的 count-form 必须要能求值为一个整数。通过每次循环，var 所持有的整数依次为从 0 到比那个数小 1 的每一个后继整数。**
- 如：
~~~lisp
CL-USER> (dotimes (i 4) (print i))
0
1
2
3
NIL
~~~
- 小应用(输出9*9乘法表)：
~~~lisp
(dotimes (i 9)
    (dotimes (j 9)
        (format t "~3d * ~d = ~3d " (+ 1 i) (+ 1 j) (* (+ 1 i) (+ 1 j))))
    (format t "~%"))
~~~