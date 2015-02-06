set nocompatible
source $VIMRUNTIME/vimrc_example.vim
source $VIMRUNTIME/mswin.vim
behave mswin

set diffexpr=MyDiff()
function MyDiff()
  let opt = '-a --binary '
  if &diffopt =~ 'icase' | let opt = opt . '-i ' | endif
  if &diffopt =~ 'iwhite' | let opt = opt . '-b ' | endif
  let arg1 = v:fname_in
  if arg1 =~ ' ' | let arg1 = '"' . arg1 . '"' | endif
  let arg2 = v:fname_new
  if arg2 =~ ' ' | let arg2 = '"' . arg2 . '"' | endif
  let arg3 = v:fname_out
  if arg3 =~ ' ' | let arg3 = '"' . arg3 . '"' | endif
  let eq = ''
  if $VIMRUNTIME =~ ' '
    if &sh =~ '\<cmd'
      let cmd = '""' . $VIMRUNTIME . '\diff"'
      let eq = '"'
    else
      let cmd = substitute($VIMRUNTIME, ' ', '" ', '') . '\diff"'
    endif
  else
    let cmd = $VIMRUNTIME . '\diff'
  endif
  silent execute '!' . cmd . ' ' . opt . arg1 . ' ' . arg2 . ' > ' . arg3 . eq
endfunction

filetype off  
" 此处规定Vundle的路径  
set rtp+=$VIM/vimfiles/bundle/vundle/  
call vundle#rc('$VIM/vimfiles/bundle/')  
Bundle 'gmarik/vundle'  
filetype plugin indent on  
  
" original repos on github<br>Bundle 'mattn/zencoding-vim'  
Bundle 'drmingdrmer/xptemplate'  
   
" vim-scripts repos  
Bundle 'L9'  
Bundle 'FuzzyFinder'  
Bundle 'bufexplorer.zip'  
Bundle 'taglist.vim'  
Bundle 'Mark'  
Bundle 'The-NERD-tree'  
Bundle 'matrix.vim'  
Bundle 'closetag.vim'  
Bundle 'The-NERD-Commenter'  
Bundle 'matchit.zip'  
Bundle 'AutoComplPop'  
Bundle 'jsbeautify'  
Bundle 'YankRing.vim'  
   
filetype plugin indent on     " required!   

set bsdir=buffer
set autochdir

"以下为解决中文显示问题,以及相应带来的提示及菜单乱码问题
set encoding=utf-8 " 设置vim内部使用的字符编码,原来是cp936  
lang messages zh_CN.UTF-8 " 解决consle输出乱码   
"解决菜单乱码   
source $VIMRUNTIME/delmenu.vim   
source $VIMRUNTIME/menu.vim 

set fileencodings=cp936,utf-8,ucs-bom,latin1
set fileencoding=utf-8
let &termencoding=&encoding

set guifont=Droid\ Sans\ Mono:h12:cANSI
set guifontwide=Microsoft\ YaHei\ Mono:h12

set nobackup

set ignorecase 

set incsearch
set hlsearch

set gdefault

set nu!

"--状态行设置--
set laststatus=2 " 总显示最后一个窗口的状态行；设为1则窗口数多于一个的时候显示最后一个窗口的状态行；0不显示最后一个窗口的状态行
set ruler            " 标尺，用于显示光标位置的行号和列号，逗号分隔。每个窗口都有自己的标尺。如果窗口有状态行，标尺在那里显示。否则，它显示在屏幕的最后一行上。

set shiftwidth=4
set tabstop=4
set softtabstop=4     " 设置软制表符的宽度 

set cindent            " 使用 C/C++ 语言的自动缩进方式
set cinoptions={0,1s,t0,n-2,p2s,(03s,=.5s,>1s,=1s,:1s     "设置C/C++语言的具体缩进方式

set showmatch        " 设置匹配模式，显示匹配的括号
set linebreak        " 整词换行
set whichwrap=b,s,<,>,[,] " 光标从行首和行末时可以跳到另一行去

set previewwindow    " 标识预览窗口
set history=50        " set command history to 50    "历史记录50条

set mouse=a            " Enable mouse usage (all modes)    "使用鼠标

set expandtab

set clipboard+=unnamed

autocmd! bufwritepost _vimrc source $VIM/_vimrc

if has("autocmd")
  au BufReadPost * if line("'\"") > 1 && line("'\"") <= line("$") | exe "normal! g'\"" | endif
  "have Vim load indentation rules and plugins according to the detected filetype
  filetype plugin indent on
endif

set smartcase        " 如果搜索模式包含大写字符，不使用 'ignorecase' 

set autowrite        " 自动把内容写回文件: 如果文件被修改过，在每个 :next、:rewind、:last、:first、:previous、:stop、:suspend、:tag、:!、:make、CTRL-] 和 CTRL-^命令时进行；用 :buffer、CTRL-O、CTRL-I、'{A-Z0-9} 或 `{A-Z0-9} 命令转到别的文件时亦然。

"自定义关联文件类型
au BufNewFile,BufRead *.less set filetype=css
au BufNewFile,BufRead *.phtml set filetype=php
au BufRead,BufNewFile *.js set ft=javascript.jquery

"---NeoComplCache 启动
let g:neocomplcache_enable_at_startup = 1 

let Tlist_Ctags_Cmd = 'C:\Ctags'

if has("syntax")
  syntax on            " 语法高亮
endif
colorscheme slate        " elflord ron peachpuff default 

set background=dark

set autoindent        " 设置自动对齐(缩进)：即每行的缩进值与上一行相等；使用 noautoindent 取消设置
set smartindent        " 智能对齐方式

"--命令行设置--
set showcmd            " 命令行显示输入的命令
set showmode        " 命令行显示vim当前模式

Bundle 'JavaScript-syntax'
Bundle 'jQuery'
Bundle 'othree/html5.vim'
Bundle 'groenewege/vim-less'
Bundle 'Markdown'
Bundle 'Markdown-syntax'
Bundle 'php.vim-html-enhanced'
Bundle 'Shougo/neocomplcache'
Bundle "MarcWeber/vim-addon-mw-utils"
Bundle "tomtom/tlib_vim"
Bundle "snipmate-snippets"
Bundle "garbas/vim-snipmate"
Bundle 'EasyMotion'
Bundle 'ack.vim'
Bundle 'calendar-vim'
Bundle 'ctrlp.vim'
Bundle 'colorselector'
Bundle 'tabular'

