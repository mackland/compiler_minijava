package parser;

import java.io.*;
import lexer.*;
import symbols.*;
import inter.*;

public class Parser {
	private Lexer lex;		//	lexical analyzer for this parser
	private Token look;		//	lookahead tagen
	Env top = null;			// top symbol table
	int used = 0;			// storage used for declarations

	public Parser(Lexer l) throws IOException {
		lex = l;
		move();
	}

	void move() throws IOException{}

	void error(String s) throws IOException{}

	void match(int t) throws IOException {}

}
