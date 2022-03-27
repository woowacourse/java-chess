package chess.domain.board.coordinate;

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
	private final int value;

	Column(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public Column move(int distance) {
		return Arrays.stream(values())
			.filter(column -> column.value == this.value + distance)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException());
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
}
