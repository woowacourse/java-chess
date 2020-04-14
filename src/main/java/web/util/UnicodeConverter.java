package web.util;

import java.util.HashMap;
import java.util.Map;

public class UnicodeConverter {
	private static Map<String, String> unicode = new HashMap<>();

	static {
		unicode.put("R", "♜");
		unicode.put("N", "♞");
		unicode.put("B", "♝");
		unicode.put("Q", "♛");
		unicode.put("K", "♚");
		unicode.put("P", "♟");

		unicode.put("r", "♖");
		unicode.put("n", "♘");
		unicode.put("b", "♗");
		unicode.put("q", "♕");
		unicode.put("k", "♔");
		unicode.put("p", "♙");

		unicode.put("", "");
	}

	public static String toUnicodeFrom(String symbol) {
		return unicode.get(symbol);
	}
}