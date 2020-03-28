package chess.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum File implements Coordinate {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private static final String ILLEGAL_FILE_NAME_EXCEPTION_MESSAGE = "올바른 열값이 아닙니다.";
	private static final int MINIMUM_DISTANCE = 1;

	private final String name;
	private final int number;

	File(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public static List<File> valuesBetween(File start, File end) {
		if (start.getNumber() > end.getNumber()) {
			return Arrays.stream(values())
				.sorted(Comparator.reverseOrder())
				.filter(file -> file.getNumber() < start.getNumber() && file.getNumber() > end.getNumber())
				.collect(Collectors.toList());
		}
		return Arrays.stream(values())
			.filter(file -> file.getNumber() > start.getNumber() && file.getNumber() < end.getNumber())
			.collect(Collectors.toList());
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

	public int getNumber() {
		return this.number;
	}

	public boolean isNear(File other) {
		return Math.abs(this.getNumber() - other.getNumber()) <= MINIMUM_DISTANCE;
	}

	public int findDifference(File other) {
		return Math.abs(this.getNumber() - other.getNumber());
	}
}
