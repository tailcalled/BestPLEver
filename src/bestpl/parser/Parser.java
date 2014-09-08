package bestpl.parser;

import java.io.IOException;
import java.util.*;

import bestpl.JavaException;

public class Parser {
	
	public static class SExpression {
		public final String op;
		public final List<SExpression> subexprs;
		public SExpression(String op, List<SExpression> subexprs) {
			this.op = op;
			this.subexprs = Collections.unmodifiableList(new ArrayList<>(subexprs));
		}
		public String toString() {
			if (subexprs.size() > 0) {
				String s = op;
				for (SExpression sexpr: subexprs) {
					s += " " + sexpr.toString();
				}
				return "(" + s + ")";
			}
			else return op;
		}
	}
	
	private final Lexer lexer;
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}
	
	public SExpression parse() throws ParserException {
		switch (lexer.curr().t) {
		case SYNCON: {
			require('(');
			String op = lexer.curr().s;
			require(Lexer.Token.Type.IDENTIFIER);
			List<SExpression> ls = new ArrayList<SExpression>();
			while (is('(') || is(Lexer.Token.Type.IDENTIFIER)) {
				ls.add(parse());
			}
			require(')');
			return new SExpression(op, ls);
		}
		case IDENTIFIER: {
			String op = lexer.curr().s;
			require(Lexer.Token.Type.IDENTIFIER);
			return new SExpression(op, Collections.<SExpression>emptyList());
		}
		case EOF:
			return null; // null is totes okay
		default:
			throw new JavaException();
		}
	}

	private void require(char c) throws ParserException {
		if (!is(c)) {
			throw new ParserException();
		}
		try {
			lexer.readtok();
		}
		catch (IOException e) {
			throw new ParserException();
		}
	}
	private boolean is(char c) {
		return lexer.curr().t == Lexer.Token.Type.SYNCON && lexer.curr().s.equals(c + "");
	}
	private void require(Lexer.Token.Type type) throws ParserException {
		if (!is(type)) {
			throw new ParserException();
		}
		try {
			lexer.readtok();
		}
		catch (IOException e) {
			throw new ParserException();
		}
	}
	private boolean is(Lexer.Token.Type t) {
		return lexer.curr().t == t;
	}
	
}
