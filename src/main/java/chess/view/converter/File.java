package chess.view.converter;

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

	private final String input;
	private final int column;

	File(String input, int column) {
		this.input = input;
		this.column = column;
	}

	static File from(String input) {
		return Arrays.stream(values())
			.filter(file -> file.input.equals(input))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_INPUT));
	}

	public int getColumn() {
		return column;
	}
}
