package chess.domain;

import java.util.Arrays;

public enum Row {
	ONE("1", 1),
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8);

	private final String name;
	private final int position;

	Row(String name, int position) {
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public Row plus() {
		return Arrays.stream(Row.values())
			.filter(value -> value.position == this.position + 1)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("9 이상의 row 값은 가질수 없습니다."));
	}

	public Row minus() {
		return Arrays.stream(Row.values())
			.filter(value -> value.position == this.position - 1)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("0 이하의 row 값은 가질수 없습니다."));
	}
}
