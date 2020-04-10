package chess.domain.position;

import java.util.Arrays;

public enum Column {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private final String name;
	private final int column;

	Column(String name, int column) {
		this.name = name;
		this.column = column;
	}

	public int minus(Column other) {
		return other.column - this.column;
	}

	public Column plus(int other) {
		return Arrays.stream(values())
			.filter(value -> value.column == this.column + other)
			.findFirst()
			.orElseThrow(AssertionError::new);
	}

	public String getName() {
		return name;
	}

	public int intValue() {
		return column;
	}
}
