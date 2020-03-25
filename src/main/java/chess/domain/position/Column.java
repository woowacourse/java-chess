package chess.domain.position;

import java.util.Arrays;

public enum Column {
	ONE(1, "a"),
	TWO(2, "b"),
	THREE(3, "c"),
	FOUR(4, "d"),
	FIVE(5, "e"),
	SIX(6, "f"),
	SEVEN(7, "g"),
	EIGHT(8, "h");

	private int value;
	private String symbol;

	Column(int value, String symbol) {
		this.value = value;
		this.symbol = symbol;
	}

	public static Column of(int col) {
		return Arrays.stream(values())
				.filter(column -> column.value == col)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 값이 입력되었습니다."));
	}

	public int calculateDistance(Column column) {
		return Math.abs(this.value - column.value);
	}
}
