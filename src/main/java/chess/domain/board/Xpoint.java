package chess.domain.board;

import java.util.Arrays;

public enum Xpoint {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	public static final String ERROR_MESSAGE = "존재하지 않는 가로열입니다.";

	private final String xPointName;
	private final int xPointValue;

	Xpoint(String xPointName, int value) {
		this.xPointName = xPointName;
		this.xPointValue = value;
	}

	public static Xpoint of(char input) {
		return Arrays.stream(values())
				.filter(value -> value.equalsTo(input))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
	}

	public static Xpoint of(int valueInput) {
		return Arrays.stream(values())
				.filter(value -> value.getValue() == valueInput)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
	}

	private boolean equalsTo(char input) {
		return this.xPointName.equals(String.valueOf(input));
	}

	public int getGapValue(Xpoint xpoint) {
		return this.xPointValue - xpoint.xPointValue;
	}

	public String getName() {
		return xPointName;
	}

	public int getValue() {
		return xPointValue;
	}
}
