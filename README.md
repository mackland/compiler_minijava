## compiler_minijava


I have finally decided to create my own compiler. This is something I have wanted to do since I first learned how to code, and would question what had originally been coded to create the program I was typing my code into.

I will be following the advice and steps given in __Compilers: Principles, Techniques, and Tools__ by _Aho et. al_ for a subset of Java. I have looked at other sources like __Modern Compiler Implementation in Java__ by _Appel_ but feel the former gives a more thorough explanation of all relevant concepts.

Want to keep the lexer and parser handwritten. First complete the front-end then move on to back-end when I have time over during the summer.

## Source language

|              |   |                                                              |
|--------------|---|--------------------------------------------------------------|
| program      | = | block                                                        |
| block        | = | {decls stmts}                                                |
| decls        | = | decls decl __e__                                         |
| decl         | = | type __id__ __;__                                            |
| type         | = | type __[__ __num__ __]__ __basic__                     |
| stmt         | = | stmts stmt __e__                                         |


Reference:
Aho et Al Compilers: Principles, Techniques, and Tools 2nd Edition 2007
