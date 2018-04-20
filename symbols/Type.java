package symbols;

import lexer.*;

public class Type extends Keyword {
    public int width = 0;

    public Type(String s, int tag, int w) {
        super(s, tag);
        width = w;
    }   

    public static Type Int      = new Type( "int",      Tag.BASIC, 4 );
    public static Type Float    = new Type( "float",    Tag.BASIC, 8 );
    public static Type Char     = new Type( "char",     Tag.BASIC, 1 );
    public static Type Bool     = new Type( "bool",     Tag.BASIC, 1 );

    public static boolean isNumeric(Type p) {
        if (p == Type.Char || p == Type.Int || p == Type.Float) {
            return true;
        } else {
            return false;
        }
    }

    public static Type max(Type p1, Type p2){
        if( !isNumeric(p1) || !isNumeric(p2) ) {
            return null;
        } else if ( p1 == Type.Float || p2 == Type.Float ) {
            return Type.Float;
        } else if ( p1 == Type.Int || p2 == Type.Int ) {
            return Type.Int;
        } else {
            return Type.Char;
        }
    }
}
