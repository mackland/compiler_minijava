public class Main {
	public static void main(String[] args) {
		try {
			new MiniJavaParser(System.in).goal();
			System.out.println("Lexical analysis successful");
		} catch(ParseException e) {
			System.out.println("Lexer error: \n" + e.toString());
		}
	}
}
