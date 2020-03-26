package domain.point;

import domain.point.exceptions.NotExistPointException;
import java.util.Arrays;

public enum Column {
	A("a"),
	B("b"),
	C("c"),
	D("d"),
	E("e"),
	F("f"),
	G("g"),
	H("h");

	private String column;

	Column(String column) {
		this.column = column;
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
