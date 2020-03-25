package domain.point;

import domain.point.exceptions.NotExistPointException;
import java.util.Arrays;

public enum Row {
	EIGHT("8", 0),
	SEVEN("7", 1),
	SIX("6", 2),
	FIVE("5", 3),
	FOUR("4", 4),
	THREE("3", 5),
	TWO("2", 6),
	ONE("1", 7);

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
}
