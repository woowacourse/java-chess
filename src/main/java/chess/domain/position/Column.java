package chess.domain.position;

import java.util.Arrays;

public enum Column {
	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8");

	private final String value;

	Column(String value) {
		this.value = value;
	}

	public static Column of(final String value) {
		return Arrays.stream(values())
			.filter(it -> it.value.equalsIgnoreCase(value))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 컬럼입니다."));
	}
}
