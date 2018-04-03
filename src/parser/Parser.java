package parser;

import java.io.*;
import lexer.*;
import symbols.*;
import inter.*;

public class Parser {
    private Lexer lex;      //  lexical analyzer for this parser
    private Token look;     //  lookahead tagen
    Env top = null;         // top symbol table
    int used = 0;           // storage used for declarations

    public Parser(Lexer l) throws IOException {
        lex = l;
        move();
    }

    //parses each token from the lexer
    void move() throws IOException{
        look = lex.scan();
    }
        
    void error(String s) throws IOException{
        throw new error("near line "+lex.line+": "+s);
    }

    void match(int t) throws IOException {
        if(look.tag == t) {
            move(); 
        } else {
            error("syntax error");
        }
    }

    public void program() throws IOException {
        Stmt s = block();

        int begin = s.newlabel();
        int after = s.newlabel();

        s.emitlabel(begin);
        s.gen(begin, after);
        s.emitlabel(after);
    }
    // match('{') makes sense but not 100 sure
    Stmt block() throws IOException{
        match('{'); Env savedEnv = top; //store previous symbol table
        top = new Env(top);

        decls();
        Stmt s = stmts();

        match('}'); top = savedEnv; // if out of block, go back to prev symbol table
        return s;
    }

    void decls() throws IOException {
        while( look.tag == Tag.BASIC ){
            Type t = type();
            Token tok = look;   match(Tag.ID);  match(';');
            Id id = new Id((Keyword) tok, t, used);
            top.put(tok, id); //add to symbol table
            used = used + p.width;
        }
    }

    Type type() throws IOException {
        Type t = (Type) look;   //expect look.tag == Tag.BASIC
        match(Tag.BASIC);
        if( look.tag != '[' ) return t; //basic type
        else return dims(t);            //array type
    }
    //uncertain of format here
    Type dims(Type t) throws IOException {
        match('['); Token tok = look; match(Tag.NUM); match(']');
        if( look.tag == '[' ) {
            t = dims(t);
        }
        return new Array(((Num)tok).value, t);
    }   

    Stmt stmts() throws IOException {
        if( look.tag == '}' ) {
            return Stmt.Null;
        } else {
            return new Seq(stmt(), stmts());
        }
    }

    Stmt stmt() throws IOException {
        Expr x; Stmt s, s1, s2;
        Stmt savedStmt;
    
        switch( look.tag ) {
            case ';':

            case Tag.IF:

            case Tag.WHILE:

            case Tag.DO:

            case Tag.BREAK:

            case '{':

            default:
        }
    }
}
