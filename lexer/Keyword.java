package lexer;

public class Keyword extends Token{

    public String lexeme = "";
    
    public Keyword(String s, int tag){
        super(tag);
        this.lexeme = s;
    }

    public String toString() {
        return lexeme;
    } 

    public static final Keyword 
            AND =       new Keyword("&&",       Tag.AND),
            OR =        new Keyword("||",       Tag.OR),
            EQUAL =     new Keyword("==",       Tag.EQ),
            N_EQUAL =   new Keyword("!=",       Tag.NE),
            G_EQUAL =   new Keyword(">=",       Tag.GE),
            L_EQUAL =   new Keyword("<=",       Tag.LE),
            TRUE =      new Keyword("true",     Tag.TRUE),
            FALSE =     new Keyword("false",    Tag.FALSE),
            IF =        new Keyword("if",       Tag.IF),
            ELSE =      new Keyword("else",     Tag.ELSE),
            BREAK =     new Keyword("break",    Tag.BREAK),
            DO =        new Keyword("do",       Tag.DO),
            WHILE =     new Keyword("while",    Tag.WHILE),
            MINUS =     new Keyword("minus",    Tag.MINUS),
            TEMP =      new Keyword("t",        Tag.TEMP);
}
