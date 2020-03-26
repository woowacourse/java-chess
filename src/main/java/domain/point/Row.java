package domain.point;

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

	public Row add(int rowIndex) {
		return find(index + rowIndex);
	}

	public static Row find(String input) {
		return Arrays.stream(values())
				.filter(row -> row.row.equals(input))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
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

	public Row subtract(Row row) {
		return find(this.index - row.index);
	}
}
