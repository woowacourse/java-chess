package domain.point;

import java.util.Arrays;

public enum Row {
	EIGHT("8", 0),
	SEVEN("7", 1),
	SIX("6", 2),
	FIVE("5", 3),
	FOUR("4", 4),
	THREE("3", 5),
	TWO("2", 6),
	ONE("1", 7);

	private final String row;
	private final int index;

	Row(String row, int index) {
		this.row = row;
		this.index = index;
	}

	public static Row find(String input) {
		return Arrays.stream(values())
				.filter(row -> row.row.equals(input))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	public int getIndex() {
		return this.index;
	}
}
