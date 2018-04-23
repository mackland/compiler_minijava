## compiler_minijava


I have finally decided to create my own compiler. This is something I have wanted to do since I first learned how to code, and would question what had originally been coded to create the program I was typing my code into.

I will be following the advice and steps given in __Compilers: Principles, Techniques, and Tools__ by _Aho et. al_ for a subset of Java. I have looked at other sources like __Modern Compiler Implementation in Java__ by _Appel_ but feel the former gives a more thorough explanation of all relevant concepts.

Want to keep the lexer and parser handwritten. First complete the front-end then move on to back-end when I have time over during the summer.

## Source language

|              |   |                                                              |
|--------------|---|--------------------------------------------------------------|
| program      | = | block                                                        |
| block        | = | {decls stmts}                                                |
| decls        | = | decls \| decl \| __e__                                         |
| decl         | = | type __id__ __;__                                            |
| type         | = | type __[__ __num__ __]__ \| __basic__                     |
| stmt         | = | stmts \| stmt __e__                                         |

## Example Program  
```
{
    int i; int j; float v; float x; float[100]; a;
    while( true ) 
        do i = i + 1; while(a[i] < v);
        do j = j - 1; while(a[j] > v);
        if( i >= j ) break;
        x = a[i]; a[i] = a[j]; a[j] = x;
    }
}
```

### Output
```
L1 :      iffalse true goto L2
L3 :      i = i + 1
L5 :      t1 = i * 8
          t2 = a [ t1 ]
          if t2 < v goto L3
L4 :      j = j - 1
L7 :      t3 = j * 8
          t4 = a [ t3 ]
          if t4 > v goto L4
L6 :      iffalse i >= j goto L8
L9 :      goto L2
L8 :      t5 = i * 8
          x = a [ t5 ]
L10 :     t6 = i * 8
          t7 = j * 8
          t8 = a [ t7 ]
          a [ t6 ] = t8
L11 :     t9 = j * 8
          a [ t9 ] = x
          goto L1
L2 :
```

Reference:
Aho et Al Compilers: Principles, Techniques, and Tools 2nd Edition 2007
