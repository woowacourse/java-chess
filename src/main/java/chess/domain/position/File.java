package chess.domain.position;

import java.util.Arrays;

public enum File {

	A('a'),
	B('b'),
	C('c'),
	D('d'),
	E('e'),
	F('f'),
	G('g'),
	H('h');

	private final char file;

	File(final char file) {
		this.file = file;
	}

	public static File from(final char value) {
		return Arrays.stream(File.values())
			.filter((file) -> value == file.file)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public char fileValue() {
		return file;
	}
}
