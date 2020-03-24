package chess.domain.position;

import java.util.Arrays;

public enum Row {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8);

	private final int value;

	Row(int value) {
		this.value = value;
	}

	public static Row of(String value) {
		return Arrays.stream(values())
			.filter(column -> Integer.parseInt(value) == column.value)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 column 값입니다."));
	}

	public Row nextRow(int rowDirection) {
		return values()[value + rowDirection - 1];
	}
}
