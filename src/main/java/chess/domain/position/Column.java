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

	public int minus(Column operand) {
		return operand.column - this.column;
	}

	public Column plus(int operand) {
		return Arrays.stream(values())
			.filter(value -> value.column == this.column + operand)
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
