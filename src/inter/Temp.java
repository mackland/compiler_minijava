package inter;

import lexer.*;
import symbols.*;

public class Temp extends Expr {
    static int count = 0;
    int number = 0;
    
    public Temp(Type t) {
        super(Keyword.temp, t);
        number = ++count;
    }

    public String toString () {
        return "t" + number;
    }
}
