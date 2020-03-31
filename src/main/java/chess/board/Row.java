package chess.board;

import java.util.Arrays;

public enum Row {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	;

	private final int number;

	Row(int number) {
		this.number = number;
	}

	public static Row of(int number) {
		return Arrays.stream(values())
			.filter(row -> row.number == number)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("Row를 찾을 수 없습니다"));
	}

	public int minus(Row other) {
		return this.number - other.number;
	}

	public boolean isGreaterThan(Row other) {
		return this.number > other.number;
	}

	public int getNumber() {
		return number;
	}

	public Row add(int rowWeight) {
		return of(this.number + rowWeight);
	}

	@Override
	public String toString() {
		return String.valueOf(number);
	}
}
