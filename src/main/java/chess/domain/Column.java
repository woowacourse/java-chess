package chess.domain;

import java.util.Arrays;

public enum Column {

	a(1),
	b(2),
	c(3),
	d(4),
	e(5),
	f(6),
	g(7),
	h(8);

	private final int value;

	Column(int value) {
		this.value = value;
	}

	public static Column of(int value) {
		return Arrays.stream(values())
			.filter(column -> column.value == value)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 위치입니다."));
	}
}
