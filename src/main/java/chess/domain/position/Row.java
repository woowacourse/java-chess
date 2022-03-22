package chess.domain.position;

import java.util.Arrays;

public enum Row {
	A("a"),
	B("b"),
	C("c"),
	D("d"),
	E("e"),
	F("f"),
	G("g"),
	H("h");

	private final String value;

	Row(String value) {
		this.value = value;
	}

	public static Row of(final String value) {
		return Arrays.stream(values())
			.filter(it -> it.value.equalsIgnoreCase(value))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 로우입니다."));
	}
}
