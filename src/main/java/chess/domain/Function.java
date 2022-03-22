package chess.domain;

import java.util.Arrays;

public enum Function {

	START("start"),
	END("end");

	private final String value;

	Function(final String value) {
		this.value = value;
	}

	public static Function from(final String value) {
		return Arrays.stream(Function.values())
			.filter(function -> function.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기능입니다."));
	}
}
