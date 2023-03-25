package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum File {

	A('a'),
	B('b'),
	C('c'),
	D('d'),
	E('e'),
	F('f'),
	G('g'),
	H('h');

	private final char value;

	File(final char value) {
		this.value = value;
	}

	public static File from(final char value) {
		return Arrays.stream(File.values())
			.filter((file) -> value == file.value)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public static List<File> files() {
		final List<Character> values = new ArrayList<>();
		for (File file : File.values()) {
			values.add(file.value);
		}
		values.sort(Comparator.naturalOrder());
		final List<File> files = new ArrayList<>();
		for (Character val : values) {
			files.add(File.from(val));
		}
		return files;
	}

	public File nextFile(final int unit) {
		return from((char)(value + unit));
	}

	public char value() {
		return value;
	}
}
