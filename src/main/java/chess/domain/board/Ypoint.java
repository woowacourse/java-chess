package chess.domain.board;

import java.util.Arrays;

public enum Ypoint {
	EIGHT("8", 8),
	SEVEN("7", 7),
	SIX("6", 6),
	FIVE("5", 5),
	FOUR("4", 4),
	THREE("3", 3),
	TWO("2", 2),
	ONE("1", 1);

	public static final String ERROR_MESSAGE = "존재하지 않는 세로행입니다.";

	private final String yPointName;
	private final int yPointValue;

	Ypoint(String yPointName, int yPointValue) {
		this.yPointName = yPointName;
		this.yPointValue = yPointValue;
	}

	public static Ypoint of(char input) {
		return Arrays.stream(values())
				.filter(value -> value.equalsTo(input))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
	}

	public static Ypoint of(int valueInput) {
		return Arrays.stream(values())
				.filter(value -> value.getValue() == valueInput)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
	}

	private boolean equalsTo(char input) {
		return this.yPointName.equals(String.valueOf(input));
	}

	public int getGapValue(Ypoint yPoint) {
		return this.yPointValue - yPoint.yPointValue;
	}

	public boolean isTwo() {
		return this == TWO;
	}

	public boolean isSeven() {
		return this == SEVEN;
	}

	public String getName() {
		return yPointName;
	}

	public int getValue() {
		return yPointValue;
	}
}
