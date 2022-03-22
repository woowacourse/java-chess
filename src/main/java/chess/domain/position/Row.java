package chess.domain.position;

import java.util.Arrays;
import java.util.Locale;

public enum Row {
	A("a"),
	B("b"),
	C("c"),
	D("d"),
	E("e"),
	F("f"),
	G("g"),
	H("h");

	private final String value;

	Row(String value) {
		this.value = value;
	}

	public static boolean contains(String string) {
		return Arrays.stream(Row.values())
			.anyMatch(row -> row.value.equalsIgnoreCase(string));
	}
}
