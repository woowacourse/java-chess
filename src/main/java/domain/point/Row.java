package domain.point;

import domain.point.exceptions.NotExistPointException;
import java.util.Arrays;

public enum Row {
	EIGHT("8", 8),
	SEVEN("7", 7),
	SIX("6", 6),
	FIVE("5", 5),
	FOUR("4", 4),
	THREE("3", 3),
	TWO("2", 2),
	ONE("1", 1);

	private final String row;
	private final int index;

	Row(String row, int index) {
		this.row = row;
		this.index = index;
	}

	public static Row find(String input) {
		return Arrays.stream(values())
				.filter(row -> row.row.equals(input))
				.findFirst()
				.orElseThrow(() -> new NotExistPointException("존재하지 않는 Row 입니다."));
	}

	@Override
	public String toString() {
		return row;
	}

	public int distance(Row row) {
		return row.index - index;
	}
}
