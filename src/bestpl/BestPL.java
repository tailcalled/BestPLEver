package bestpl;

import java.io.*;

import bestpl.interpreter.*;
import bestpl.parser.Parser.SExpression;
import bestpl.parser.*;

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
		Parser parser = new Parser(new Lexer(new FileReader(file)));
		Interpreter interpreter = new DefaultInterpreter();
		SExpression sexpr;
		while ((sexpr = parser.parse()) != null) {
			interpreter.interpret(sexpr);
		}
	}

}