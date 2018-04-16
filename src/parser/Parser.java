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
        
    void error(String s) {
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
    private Stmt block() throws IOException{
        match('{'); Env savedEnv = top; //store previous symbol table
        top = new Env(top);

        decls();
        Stmt s = stmts();

        match('}'); top = savedEnv; // if out of block, go back to prev symbol table
        return s;
    }

    private void decls() throws IOException {
        while( look.tag == Tag.BASIC ){
            Type t = type();
            Token tok = look;   match(Tag.ID);  match(';');
            Id id = new Id((Keyword) tok, t, used);
            top.put(tok, id); //add to symbol table
            used = used + p.width;
        }
    }

    private Type type() throws IOException {
        Type t = (Type) look;   //expect look.tag == Tag.BASIC
        match(Tag.BASIC);
        if( look.tag != '[' ) return t; //basic type
        else return dims(t);            //array type
    }
    //uncertain of format here
    private Type dims(Type t) throws IOException {
        match('['); Token tok = look; match(Tag.NUM); match(']');
        if( look.tag == '[' ) {
            t = dims(t);
        }
        return new Array(((Num)tok).value, t);
    }   

    private Stmt stmts() throws IOException {
        if( look.tag == '}' ) {
            return Stmt.Null;
        } else {
            return new Seq(stmt(), stmts());
        }
    }

    private Stmt stmt() throws IOException {
        Expr x; Stmt s, s1, s2;
        Stmt savedStmt;
    
        switch( look.tag ) {
            case ';':
                move();
                return Stmt.Null;
            case Tag.IF:
                match(Tag.IF); match('('); x = bool(); match(')');
                s1 = stmt();
                if( look.tag != Tag.ELSE ) {
                    return new If(x, s1);
                }
                match(Tag.ELSE);
                s2 = stmt();
                return new Else(x, s1, s2);
            case Tag.WHILE:
                While whilenode = new While();
                savedStmt = Stmt.Enclosing; Stmt.Enclosing = whilenode;
                match(Tag.WHILE); match('('); x = bool(); match(')');
                s1 = stmt();
                whilenode.init(x, s1);
                Stmt.Enclosing = savedStmt;
                return whilenode;
            case Tag.DO:
                Do donode = new Do();
                savedStmt = Stmt.Enclosing; Stmt.Enclosing = donode;
                match(Tag.DO);
                s1 = stmt();
                match(Tag.WHILE); match('('); x = bool(); match(')'); match(';');
                donode.init(s1, x);
                Stmt.Enclosing = savedStmt;
                return donode;
            case Tag.BREAK:
                match(Tag.BREAK); match(';');
                return new Break();
            case '{':
                return block();
            default:
                return assign();
        }
    }

    private Stmt assign() throws IOException {
        Stmt stmt; Token t = look;
        match(Tag.ID);
        Id id = top.get(t);
        if( id == null) {
            error(t.toString() + " undeclared");
        }
        if( look.tag == '>=' ) {
            move();
            stmt = new Set(id, bool());
        } else {
            Access x = offset(id);
            match('=');
            stmt = new SetElem(x, bool());
        }
        match(';');
        return stmt;
    }
}
