package chess.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum File {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private static final String ILLEGAL_FILE_NAME_EXCEPTION_MESSAGE = "올바른 열값이 아닙니다.";

	private final String name;

	File(String name) {
		this.name = name;
	}

	public static List<File> valuesBetween(File start, File end) {
		File smaller = min(start, end);
		File bigger = max(start, end);

		List<File> files = new ArrayList<>();
		for (File file : values()) {
			if (between(file, smaller, bigger)) {
				files.add(file);
			}
		}
		files.remove(start);
		return files;
	}

	private static boolean between(File target, File smaller, File bigger) {
		return target.compareTo(smaller) >= 0 && target.compareTo(bigger) <= 0;
	}

	private static File max(File file1, File file2) {
		if (file1.compareTo(file2) > 0) {
			return file1;
		} else {
			return file2;
		}
	}

	private static File min(File file1, File file2) {
		if (file1.compareTo(file2) < 0) {
			return file1;
		} else {
			return file2;
		}
	}

	public static File of(String name) {
		return Arrays.stream(values())
			.filter(value -> value.name.equals(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_FILE_NAME_EXCEPTION_MESSAGE));
	}

	public String getName() {
		return name;
	}
}
