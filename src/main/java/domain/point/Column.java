package domain.point;

import domain.point.exceptions.NotExistPointException;
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
}
