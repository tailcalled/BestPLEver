package bestpl;

import java.io.*;

import bestpl.parser.Lexer;
import bestpl.parser.Parser;
import bestpl.parser.ParserException;

public class BestPL {

	public static void print(Object... args) {
		for (Object x: args) {
			System.out.print(x);
		}
		System.out.println();
	}

	public static void main(String[] args) throws ParserException, IOException {
		if (args.length != 1) {
			print("Usage:");
			print("   bestpl [file]");
		}
		File file = new File(args[0]);
		print(new Parser(new Lexer(new FileReader(file))).parse());
	}

}