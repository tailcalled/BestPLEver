package bestpl.parser;

import java.io.*;

public class Lexer {
	
	public static class Token {
		public static enum Type { IDENTIFIER, SYNCON, EOF }
		public final String s;
		public final Type t;
		public Token(String s, Type t) {
			this.s = s;
			this.t = t;
		}
		public String toString() {
			return t + "('" + s + "')";
		}
	}
	
	public static Token EOF = new Token("", Token.Type.EOF);
	
	private final Reader input;
	private Token curr;
	private char currCh;
	private boolean eof = false;
	
	public Lexer(Reader input) throws IOException {
		this.input = input;
		readch();
		readtok();
	}
	
	public Token curr() {
		return curr;
	}
	public Token readtok() throws IOException {
		curr = _readtok();
		return curr;
	}
	private Token _readtok() throws IOException {
		while (Character.isWhitespace(currCh) && !eof) {
			readch();
		}
		if (eof) return EOF;
		if (currCh == '(' || currCh == ')') {
			char c = currCh;
			readch();
			return new Token(c + "", Token.Type.SYNCON);
		}
		else {
			String s = "";
			do {
				s += currCh;
			} while (readch() && !Character.isWhitespace(currCh) && currCh != '(' && currCh != ')');
			return new Token(s, Token.Type.IDENTIFIER);
		}
	}

	public boolean readch() throws IOException {
		int ch = input.read();
		if (ch == -1) {
			eof = true;
			return false;
		}
		currCh = (char) ch;
		return true;
	}
	
}