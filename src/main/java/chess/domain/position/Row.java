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

	private int symbol;

	Row(int symbol) {
		this.symbol = symbol;
	}

	public static Row of(String row) {
		try {
			return of(Integer.parseInt(row));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Row는 숫자만 입력할 수 있습니다.");
		}
	}

	public static Row of(int row) {
		return Arrays.stream(values())
				.filter(r -> r.symbol == row)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 값이 입력되었습니다."));
	}

	public static int size() {
		return values().length;
	}

	public int calculateDistance(Row row) {
		return Math.abs(calculateDifference(row));
	}

	public int calculateDifference(Row row) {
		return this.symbol - row.symbol;
	}

	public int getSymbol() {
		return symbol;
	}
}
