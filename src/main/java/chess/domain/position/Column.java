package chess.domain.position;

import java.util.Arrays;
import java.util.function.Predicate;

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
		return filterBy(column -> column.value == col);
	}

	public static Column of(String col) {
		return filterBy(column -> column.symbol.equals(col));
	}

	private static Column filterBy(Predicate<Column> predicate) {
		return Arrays.stream(values())
				.filter(predicate)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 값이 입력되었습니다."));
	}

	public int calculateDistance(Column column) {
		return Math.abs(calculateDifference(column));
	}

	public int calculateDifference(Column column) {
		return this.value - column.value;
	}

	public int getValue() {
		return value;
	}
}
