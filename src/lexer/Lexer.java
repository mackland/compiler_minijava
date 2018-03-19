package lexer;

public class Lexer {

	private HashMap<String Keyword> words = new HashMap<>();
	
	private char peek = ' ';
	private int currentLine = 1;
	
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
		if(Character.isDigit(peek)){}
		
		//add stringbuilder etc
		if(Character.isLetter((int) peek)){}
	}
}
