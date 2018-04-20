package inter;

import lexer.*;
import symbols.*;

public class Op extends Expr {
    public Op(Token tok, Type t) {
        super(tok, t);
    }

    public Expr reduce() {
        Expr x = gen();
        temp tmp = new Temp(type);
        emit( t.toString() + " = " + x.toString() );
        return tmp;
    }
}
