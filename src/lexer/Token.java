package lexer;

public class Token {

	private final int tag;

	public int Token(int t) {
		this.tag = t;
	}

	public String toString() {
		return "" + (char) tag;
	}
}
