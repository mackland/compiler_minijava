package inter;

import lexer.*;
import symbols.*;

public class Id extends Expr {
    public int offset;
    public Id(Keyword id, Type t, int b){
        super(id, p);
        offset = b;
    }
}
