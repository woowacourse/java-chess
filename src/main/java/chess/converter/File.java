package chess.converter;

import java.util.Arrays;

public enum File {

	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private static final String INVALID_FILE_INPUT = "File은 a~h 사이에서 입력해야 합니다.";

	private final String name;
	private final int column;

	File(String name, int column) {
		this.name = name;
		this.column = column;
	}

	public static File from(String name) {
		return Arrays.stream(values())
			.filter(file -> file.name.equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_INPUT));
	}

	public static File from(int column) {
		return Arrays.stream(values())
			.filter(file -> file.column ==  column)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_INPUT));
	}

	public int getColumn() {
		return column;
	}

	public String getName() {
		return name;
	}
}
