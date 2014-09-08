package bestpl;

import java.io.*;

public class BestPL {

	public static void print(Object... args) {
		for (Object x: args) {
			System.out.print(x);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			print("Usage:");
			print("   bestpl [file]");
		}
		File file = new File(args[0]);

	}

}