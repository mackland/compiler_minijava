package inter;

import lexer.*; import symbols.*;

public class Constant extends Expr {
    public Constant(Token tok, Type t) {
        super(tok, t);
    }

    //overloaded for convenience
    public Constant(int i) {
        super(new Num(i), Type.Int);
    }

    public static final Constant
        True = new Constant(Keyword.True,   Type.Bool),
        False = new Constant(Keyword.False, Type.Bool);

    public void jumpting(int t, int f) {
        if( this == True && t != 0){
            emit("goto L" + t);
        } else if ( this == False && f != 0) {
            emit("goto L" + f);
        }
    }
}
