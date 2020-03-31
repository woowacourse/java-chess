package chess.domain.chessPiece.position;

import java.util.Arrays;

public enum File {
	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private final String name;
	private final int number;

	File(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public static File of(String fileValue) {
		return Arrays.stream(File.values())
				.filter(x -> x.name.equals(fileValue))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}

	public static File of(int number) {
		return Arrays.stream(File.values())
				.filter(x -> x.number == number)
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public int calculateDistance(File file) {
		return this.number - file.number;
	}
}
