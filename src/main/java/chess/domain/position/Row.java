package chess.domain.position;

import java.util.Arrays;

public enum Row {
	EIGHT("8", 8),
	SEVEN("7", 7),
	SIX("6", 6),
	FIVE("5", 5),
	FOUR("4", 4),
	THREE("3", 3),
	TWO("2", 2),
	ONE("1", 1);

	private final String name;
	private final int row;

	Row(String name, int row) {
		this.name = name;
		this.row = row;
	}

	public int minus(Row other) {
		return other.row - this.row;
	}

	public Row plus(int other) {
		return Arrays.stream(values())
			.filter(value -> value.row == this.row + other)
			.findFirst()
			.orElseThrow(AssertionError::new);
	}

	public String getName() {
		return name;
	}

	public int intValue() {
		return row;
	}
}
