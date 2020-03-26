package domain.point;

import domain.point.exceptions.NotExistPointException;
import java.util.Arrays;

public enum Row {
	EIGHT("8"),
	SEVEN("7"),
	SIX("6"),
	FIVE("5"),
	FOUR("4"),
	THREE("3"),
	TWO("2"),
	ONE("1");

	private final String row;

	Row(String row) {
		this.row = row;
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
