package domain.point;

import domain.point.exceptions.NotExistPointException;
import java.util.Arrays;

public enum Column {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private String column;
	private int index;

	Column(String column, int index) {
		this.column = column;
		this.index = index;
	}

	public static Column find(String input) {
		return Arrays.stream(values())
				.filter(column -> column.column.equals(input))
				.findFirst()
				.orElseThrow(() -> new NotExistPointException("존재하지 않는 Column 입니다."));
	}

	@Override
	public String toString() {
		return column;
	}

	public int distance(Column column) {
		return column.index - index;
	}
}
