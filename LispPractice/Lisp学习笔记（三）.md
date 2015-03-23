****
#Lisp学习笔记（三）
- author: Y.R.H
- time: 2015-3-12
<!--more-->

****

#函数

##函数定义
~~~~lisp
(defun name (parameter*)
    "Optional documentation string."
    boby-form*)
~~~~

##函数命名及描述文档
- 任何符号都可用作函数名。通常函数名仅包含字典符和连字符，但是在特定的命名约定里，其他字符也允许使用。
- 如果一个字符串紧跟在形参列表之后，那么它应该是一个用来描述函数用途的文档字符串。当定义函数时，该文档字符串将被关联到函数名上，并且以后可以通过 DOCUMENTATION 函数来获取。例如：(documentation 'foo 'function) 将返回 foo 的文档字符串。

##函数返回值
- 一个 DEFUN 的主体可由任意数量的 Lisp 表达式所构成，它们将在函数被调用时依次求值，而最后一个表达式的值将被作为整个函数的值返回。另外 RETURN-FROM 特殊操作符可用于从函数的任何位置立即返回。

##一个复杂的函数实例
~~~~lisp
(defun verbose-sum (x y)
    "Sum any two numbers after printing a message."
    (format t "Summing ~d and ~d.~%" x y)
    (+ x y))
~~~~

##可选形参
- 为了定义一个带有可选形参的函数，在必要形参的名字之后放置符号 &optional ，后接可选形参的名字。
- 如：(defun foo (a b &optional c d) (list a b c d))

- 调用结果：
~~~lisp
(foo 1 2)           →   (1 2 NIL NIL)
(foo 1 2 3)         →   (1 2 3 NIL)
(foo 1 2 3 4)       →   (1 2 3 4)
~~~

- 如果想用一个特定的值来指定为默认值，则可以用一个表达式来实现：
~~~lisp        
(defun foo (a &optional (b 10)) (list a b))
~~~

- 调用结果：
~~~lisp
(foo 1 2)               →   (1 2)
(foo 1)                 →   (1 10)
~~~

- 如果需要更灵活地选择默认值。比如可能想要基于其他形参来计算默认值。默认值表达式可以引用早先出现在形参列表中的形参。如果要编写一个返回矩形的某种表示的函数，并且想要使它可以特别方便地产生正方形，那么可以使用如下形式：
~~~lisp
(defun make-rectangle (width &optional (height width)) ...)
~~~

- 有时，有必要去了解一个可选形参的值究竟是被调用者明确指定还是使用了默认值。可以通过在形参标识符的默认值表达式之后添加另一个变量名来做到，该变量将在调用者实际为该形参提供了一个实参时绑定到真值，否则为 NIL 。通常约定，这种变量的名字与对应的真实形参相同，但带有一个 -supplied-p 后缀，如：
~~~lisp
(defun foo (a b &optional (c 3 c-supplied-p))
    (list a b c c-supplied-p))
~~~

##剩余形参
- Lisp 允许在符号 &rest 之后包括一揽子形参。如果函数带有 &rest 形参，那么任何满足了必要和可选形参之后的其余所有实参就将被收集到一个列表里成为该 &rest 形参的值。 这样，FORMAT 和 + 的形参列表可能看起来会是这样：
~~~lisp
(defun format (stream string &rest values) ...)
(defun + (&rest numbers) ...)
~~~

##关键字形参
- 它允许调用者指定具体形参相应所使用的值。
- 为了使函数带有关键字形参，在任何必要的 &optional 和 &rest 形参之后，可以加上符号 &key 以及任意数量的关键字形参标识符，后者的格式类似于可选形参标识符。
- 如：（只有关键字形参的函数）
~~~lisp
(defun foo (&key a b c) (list a b c))
~~~
- 如果一个给定的关键字没有出现在实参列表中，那么对应的形参将被赋予其默认值，如同可选形参那样。
- 调用结果：
~~~lisp
(foo)                   →   (NIL NIL NIL)
(foo :a 1)              →   (1 NIL NIL)
(foo :b 1)              →   (NIL 1 NIL)
(foo :c 1)              →   (NIL NIL 1)
(foo :a 1 :c 3)         →   (1 NIL 3)
(foo :a 1 :b 2 :c 3)    →   (1 2 3)
(foo :c 3 :a 1 :b 2)    →   (1 2 3)
~~~

- 如同可选形参一样，关键字形参也可以提供一个默认值形式以及一个 -supplied-p 变量名。这个默认值都可以引用早先出现在形参列表中的形参。
~~~lisp
(defun foo (&key (a 0) (b 0 b-supplied-p) (c (+ a b)))
    (list a b c b-supplied-p))  
~~~
- 调用结果：
~~~lisp
(foo :a 1)              →   (1 0 1 NIL)
(foo :b 1)              →   (0 1 1 T)
(foo :b 1 :c 4)         →   (0 1 4 T)
(foo :a 2 :b 1 :c 4)    →   (2 1 4 T)
~~~

- 如果想要让调用者用来指定形参的关键字不同于实际形参名，可以将形参名替换成一个列表，令其含有调用函数时使用的关键字以及用作形参的名字。
~~~lisp
(defun foo (&key ((:apple a)) ((:box b) 0) ((:charlie c) 0 c-supplied-p))
    (list a b c c-supplied-p))
~~~
- 调用结果：
~~~lisp
(foo :apple 10 :box 20 :charlie 30)     →   (10 20 30 T)
~~~

##混合不同的形参类型
###当用到多种类型的形参时，它们必须以这样的顺序声明：
- （1）必要形参
- （2）可选形参
- （3）剩余形参
- （4）关键字形参
- **提示：一般不会将 &optional 或者 &rest 和 &key 形参组合使用。**

##函数返回值
- RETURN-FROM 特殊操作符，它能立即以任何值从函数中间返回。
- 它还可以从一个由 BLOCK 特殊操作符所定义的代码块中返回。
~~~lisp
(defun foo (n)
    (dotimes (i 10)
        (dotimes (j 10)
            (when (> (* i j) n)
                (return-from foo (list i j))))))
~~~

##高阶函数——作为数据的函数
- 特殊操作符 FUNCTION 提供了用来获取一个函数对象的方法。它接受单一实参并返回与该参数同名的函数。这个名字是不被引用的。因此如果一个函数 foo 的定义如下：
~~~lisp
CL-USER> (defun foo (x) (* 2 x))
FOO
CL-USER> (function foo)
#<Interpreted Function FOO>
~~~

- 如同 (') 是 QUOTE 的语法糖一样。(#') 是 FUNCTION 的语法糖。因此：
~~~lisp
CL-USER> #'foo
#<Interpreted Function FOO>
~~~

- Common Lisp 提供了两个函数用来通过函数对象调用函数： FUNCALL 和 APPLY。
- 它们的区别仅在于如何获取传递给函数的实参。

###FUNCALL
- 用于在编写代码时确切知道传递给函数多少实参时。FUNCALL 的第一个实参是被调用的函数对象，其余的实参被传递到该函数中。
~~~lisp
(foo 1 2 3) ≡ (funcall #'foo 1 2 3)
~~~

- 下面这个函数演示了 FUNCALL 的另一个更有建设性的用法。它接受一个函数对象作为实参，并使用实参函数在 min 和 max 之间以 step 为步长的返回值来绘制一个简单的ASCII式柱状图：
~~~lisp
(defun plot (fn min max step)
    (loop for i from min to max by step do
        (loop repeat (funcall fn i) do (format t "*"))
        (format t "~%")))
~~~

###APPLY
- 和 FUNCALL 一样，APPLY 的第一个参数是一个函数对象。但在这个函数对象之后，它期待一个列表而非单独的实参。它将函数应用在列表的值上：
~~~lisp
(apply #'plot plot-data)    ; 假如该函数需要的参数以列表的形式存在 plot-data 中
~~~

- 更方便的是，APPLY 还接受“孤立”(loose)的实参，只要最后一个参数是一个列表就行：
~~~lisp
(apply #'plot #'exp plot-data)
~~~

##匿名函数
~~~lisp
(lambda (parameters) body)
~~~
- 可以这么用：
~~~lisp
(funcall #'(lambda (x y) (+ x y)) 2 3)      →   5
~~~
- 还可以这么用：
~~~lisp
((lambda (x y) (+ x y)) 2 3)        → 5 ; 几乎没人这么做
~~~
- 示例：
~~~lisp
CL-USER> (plot #'(lambda (x) (* 2 x)) 0 10 1)
~~~
- LAMBDA 表达式的另一项重要用途是制作闭包 (closure)，即捕捉了其创建时环境信息的函数。