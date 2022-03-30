package chess.domain.position;

import java.util.Arrays;

public enum File {
	A('a', 1),
	B('b', 2),
	C('c', 3),
	D('d', 4),
	E('e', 5),
	F('f', 6),
	G('g', 7),
	H('h', 8);

	private static final String BUG_MESSAGE_BOUND = "[BUG] 체스판 범위를 벗어났습니다.";
	private final char value;
	private final int index;

	File(char value, int index) {
		this.value = value;
		this.index = index;
	}

	static File find(char value) {
		return Arrays.stream(values())
			.filter(file -> file.value == value)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(BUG_MESSAGE_BOUND));
	}

	int getGap(File target) {
		return target.index - this.index;
	}

	File add(int dFile) {
		return Arrays.stream(values())
			.filter(file -> file.index == this.index + dFile)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(BUG_MESSAGE_BOUND));
	}
}
