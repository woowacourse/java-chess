package chess.domain;

public enum Row {
	FIRST("a", 1),
	SECOND("b", 2),
	THIRD("c", 3),
	FOURTH("d", 4),
	FIFTH("e", 5),
	SIXTH("f", 6),
	SEVENTH("g", 7),
	EIGHTH("h", 8);

	private final String name;
	private final int value;

	Row(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static Row of(String name) {
		for (Row row : Row.values()) {
			if (row.name.equals(name)) {
				return row;
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
}
