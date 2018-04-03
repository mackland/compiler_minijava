package lexer;

public class Keyword extends Token{

    public final String lexeme;
    
    public Keyword(String lexeme, int tag){
        super(tag);
        this.lexeme = lexeme;
    }

    public static final Keyword AND = new Keyword("&&", Tag.AND),
            OR = new Keyword("||", Tag.OR),
            EQUAL = new Keyword("==", Tag.EQ),
            N_EQUAL = new Keyword("!=", Tag.NE),
            G_EQUAL = new Keyword(">=", GE),
            L_EQUAL = new Keyword("<=", LE),
            TRUE = new Keyword("true", TAG.TRUE),
            FALSE = new Keyword("false", TAG.FALSE),
            IF = new Keyword("if", TAG.IF),
            ELSE = new Keyword("else", TAG.ELSE),
            BREAK = new Keyword("break", TAG.BREAK),
            DO = new Keyword("do", TAG.DO),
            WHILE = new Keyword("while", TAG.WHILE),
            MINUS = new Keyword("minus", TAG.MINUS);
}
