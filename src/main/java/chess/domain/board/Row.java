package chess.domain.board;

import java.util.Arrays;

public enum Row {

	FIRST(1),
	SECOND(2),
	THIRD(3),
	FOURTH(4),
	FIFTH(5),
	SIXTH(6),
	SEVENTH(7),
	EIGHTH(8);

	private final int value;

	Row(int value) {
		this.value = value;
	}

	public static Row of(int value) {
		return Arrays.stream(values())
			.filter(row -> row.value == value)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 위치입니다."));
	}
}
