package chess.domain;

import java.util.Arrays;

public enum Winner {
	WHITE(-1),
	BLACK(1),
	NONE(0);

	private final int value;

	Winner(final int value) {
		this.value = value;
	}

	public static Winner from(final int compared) {
		return Arrays.stream(values())
			.filter(winner -> winner.value == compared)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("승자를 불러오는데 에러가 발생했습니다."));
	}

	public static Winner of(final double blackScore, final double whiteScore) {
		final int compared = Double.compare(blackScore, whiteScore);
		return Arrays.stream(values())
			.filter(winner -> winner.value == compared)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("승자를 불러오는데 에러가 발생했습니다."));
	}
}
