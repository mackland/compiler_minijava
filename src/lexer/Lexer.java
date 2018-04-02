package lexer;

public class Lexer {

    private HashMap<String, Keyword> words = new HashMap<>();
    
    private char peek = ' ';
    private int currentLine = 1;
    
    public Lexer() {
        reserve( new Keyword("if",      Tag.IF)     );
        reserve( new Keyword("else",    Tag.ELSE)   );
        reserve( new Keyword("while",   Tag.WHILE)  );
        reserve( new Keyword("do",      Tag.DO)     );
        reserve( new Keyword("break",   Tag.BREAK)  );
        reserve( Keyword.True   );  reserve( Keyword.False  );
        reserve( Keyword.Int    );  reserve( Type.Char      );
        reserve( Type.Bool      );  reserve( Type.Float     );
    }

    private void read() {
        peek = (char) System.in.read();
    }

    private boolean read(char c) {
        read();
        if(peek != c) {
            return false;
        } else {
            peek = ' ';
            return true;
        }
    }

    public Token scan() {
        for(;; read()) {
            if(peek == ' ' || peek == '\t') {
                //continue; whitespace
            } else if (peek == '\n') {
                currentLine++; 
            } else {
                break;
            }       
        }

        //handling comments
        if(peek == '/') {
            //single
            if(read('/') {
                for(;;read()){
                    if(peek == '\n') {
                        break;
                    }
                }
            /*multi-line, requires 2 lookahead*/
            } else if(read('*')) {
                prevPeek = ' ';
                for(;;prevPeek = peek, read()){
                    if(prevPeek == '*' && peek == '/'){
                        break;
                    }
                }
            } else {
                throw new SyntaxError();
            }
        }

        switch(peek) {
            case '&':
                if(read('&')) {
                    return Keyword.AND;
                } else {
                    return new Token('&');
                }
            case '|':
                if(read('|')) {
                    return Keyword.OR;
                } else {
                    return new Token('|');
                }
            case '>':
                if(read('=')){ 
                    return Keyword.G_EQUAL;
                } else {
                    return new Token('>');
                }
            case '<':
                if(read('=')){
                    return Keyword.L_EQUAL;
                } else {
                    return new Token('<');
                }
            case '=':
                if(read('=')) {
                    return Keyword.EQUAL;
                } else {
                    return new Token('=');
                }
            case '!':
                if(read('=')) {
                    return Keyword.N_EQUAL;
                } else {
                    return new Token('!');
                }
        }
        
        //multiply by ten to get number etc
        if(Character.isDigit(peek)){
            int v = 0;
            do {
                v = 10*v + Character.digit(peek, 10);
                read(); 
            } while ( Character.isDigit(peek) );
            if( peek != '.' ) {
                return new Num(v);
            }
            float x = v;
            float d = 10;
            for(;;){
                read();
                if(!Character.isDigit(peek)) {
                    break;
                }
                x = x + Character.digit(peek, 10) / d;
                d = d*10;
            }
            return new Real(x);
        }
        
        //add stringbuilder etc
        if(Character.isLetter((int) peek)){
            StringBuffer b = new StringBuffer();
            do{
                b.append(peek);
                read();
            } while( Character.isLetterOrDigit(peek));
            
            String s = b.toString();
            if(!keywords.containsKey(s))    {
                keywords.put(s, new Keyword(s, Tag.ID));
            }
            return keywords.get(s);
        }

        Token tok = new Token(peek);
        peek = ' ';
        return tok;
    }
}
