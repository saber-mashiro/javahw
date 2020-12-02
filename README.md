# java课设 #
# 英语六级单词对战程序
学了Java的 **socket** 运用，今天终于写完了
******************
## 游戏简介
- 初始每人有20点生命值，生命值先用完的一方输掉比赛
- 对战双方同时下降的是同一个单词，但是单词的缺失空位可能不尽相同，若有一方先完成输入，该选手加一点生命值，对手减少一点；反之若输入错误，该选手减少一点生命值，对手增加一点
- 若单词下降到最下方，双方都还未作答，双方同时减少一点生命值
- 游戏结束时，会存储两个文件`right.txt`和`wrong.txt`,以便之后的复习
*********************
## 课设心得
***********************
### 简单的正则表达式的使用方法
     `\s` 匹配空白字符，如`[<空格>\t\r\n\f\v]`
     `\S` 匹配非空白字符 `[^\s]`
     `\d` 匹配数字 `[0-9]`
     `\D` 匹配非数字 `[^\d]`
     `\w` 匹配单词字符 `[a-z A-Z 0-9]`
     `\W` 匹配非单词字符 `[^\w]`
- **数量词（用于字符之后）**：`*` 表示匹配前一个字符0次或者无限次；`+` 表示匹配前一个字符1次或者无限次；`?` 表示匹配前一个字符0次或者1次；`{m}` 表示匹配前一个字符m次；`{m,n}`表示匹配前一个字符m到n次（m，n均可省略）；
- **边界匹配**：`^` 匹配字符串的开头（可匹配多行）；`$` 匹配字符串的结尾（可匹配多行）；`\A` 仅匹配字符串的开头；`\Z` 仅匹配字符串的末尾；`\b` 匹配`\w`与`\W`之间；`\B` 匹配`[^\b]`
***************
### 文件的读写
- 读写文件均可使用**相对路径**，但是debug的时候要使用**绝对路径** ~~别问我为什么知道~~
***************
### 键盘监听器的使用（尤其是`keyPressed`，这个折腾了好久）
- 每一次按下键盘都会被监听到，如果想要实现连续监听多次，我是使用`if`的条件判断来实现的，判断过程中需要注意控制变量的定义，因为每次按下键盘 `keyPressed`都会执行一次，变量定义在`keyPressed`里面可能会导致每次都会被赋初值，虽然每次都被监听到，但是却不会执行之后的代码，今后需要注意这一点
******************
### java中char与String的相互转换
- **String 转 Char**
    - 使用 `String.charAt(index)`（返回值为char）可以得到String中某一指定位置的char
    - 使用`String.toCharArray()`（返回值为char[]）可以得到将包含整个String的char数组。这样我们就能够使用从0开始的位置索引来访问string中的任意位置的元素
- **char 转 String**
    - `String s = String.valueOf('c');` //效率最高的方法

    - `String s = String.valueOf(new char[]{'c'});` //将一个char数组转换成String
    - `String s = Character.toString('c');`// Character.toString(char)方法实际上直接返回String.valueOf(char)
    - `String s = new Character('c').toString();`
    - ` String s = "" + 'c';`// 虽然这个方法很简单，但这是效率最低的
    - `String s = new String(new char[]{'c'});`//转 ***char数组*** 用（因为用错方法，修了好久bug）
**************
### `Runnable, ActionListener,KeyListener`接口的使用，`JFrame，JPanel`的使用
**************
### 随机数的生成
- ```java
    private Random rnd = new Random();
    int wn = rnd.nextInt(2000);//左闭右开区间
- ```java
    int num = (int) (Math.random() * k);
**************
### 
