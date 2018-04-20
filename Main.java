import java.io.*; import lexer.*;

public class Main {
    public static void main(String[] args) throws IOException {
        while(true) {
            Lexer lex = new Lexer();
            Token tok = lex.scan();
            System.out.println(tok);
        }
    }
}
