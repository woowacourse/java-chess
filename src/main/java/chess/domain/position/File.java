package chess.domain.position;

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

	public static List<File> getFiles(){
		final List<File> files = Arrays.asList(File.values());
		files.sort(Comparator.naturalOrder());
		return files;
	}

	public char fileValue() {
		return file;
	}

	public File setNextFile(final int unit) {
		return from((char)(file + unit));
	}
}
