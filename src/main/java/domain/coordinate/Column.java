package domain.coordinate;

import domain.coordinate.exceptions.CoordinateException;

import java.util.Arrays;

public enum Column {
	A("a", 0),
	B("b", 1),
	C("c", 2),
	D("d", 3),
	E("e", 4),
	F("f", 5),
	G("g", 6),
	H("h", 7);

	private final String representation;
	private final int index;

	Column(String representation, int index) {
		this.representation = representation;
		this.index = index;
	}

	public Column add(int columnIndex) {
		return find(index + columnIndex);
	}

	public static Column find(String input) {
		return Arrays.stream(values())
				.filter(column -> column.representation.equals(input))
				.findFirst()
				.orElseThrow(() -> new CoordinateException(input + " 열을 찾을 수 없습니다."));
	}

	private Column find(int index) {
		return Arrays.stream(values())
				.filter(column -> column.index == index)
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	public int getIndex() {
		return index;
	}

	public String getRepresentation() {
		return representation;
	}
}
