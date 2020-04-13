package domain.coordinate;

import domain.coordinate.exceptions.CoordinateException;

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

	private final String representation;
	private final int index;

	Row(String representation, int index) {
		this.representation = representation;
		this.index = index;
	}

	public Row add(int rowIndex) {
		return find(index + rowIndex);
	}

	public static Row find(String input) {
		return Arrays.stream(values())
				.filter(row -> row.representation.equals(input))
				.findFirst()
				.orElseThrow(() -> new CoordinateException(input + " 행을 찾을 수 없습니다."));
	}

	public static Row find(int index) {
		return Arrays.stream(values())
				.filter(row -> row.index == index)
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	public int getIndex() {
		return this.index;
	}

	public String getRepresentation() {
		return representation;
	}
}
