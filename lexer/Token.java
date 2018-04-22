package lexer;

public class Token {

    public int tag;

    public Token(int t) {
        this.tag = t;
    }

    public String toString() {
        return "" + (char) tag;
    }
}
