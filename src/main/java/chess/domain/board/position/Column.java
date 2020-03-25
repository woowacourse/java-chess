package chess.domain.board.position;

import java.util.Arrays;

public enum Column {
	FIRST("1", 1),
	SECOND("2", 2),
	THIRD("3", 3),
	FOURTH("4", 4),
	FIFTH("5", 5),
	SIXTH("6", 6),
	SEVENTH("7", 7),
	EIGHTH("8", 8);

	private final String name;
	private final int value;

	Column(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static Column of(String name) {
		for (Column column : Column.values()) {
			if (column.name.equals(name)) {
				return column;
			}
		}
		throw new IllegalArgumentException("옳지 않은 좌표 입력입니다.");
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public Column calculate(int value) {
		return Arrays.stream(Column.values())
				.filter(column -> column.value == this.value + value)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(("옳지 않은 좌표 입력입니다.")));
	}
}
