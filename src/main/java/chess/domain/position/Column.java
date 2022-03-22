package chess.domain.position;

import java.util.Arrays;

public enum Column {
	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8");

	private final String value;

	Column(String value) {
		this.value = value;
	}

	public static boolean contains(String string) {
		return Arrays.stream(Column.values()).anyMatch(column -> column.value.equals(string));
	}
}
