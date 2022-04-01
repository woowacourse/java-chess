package chess.domain.board;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public enum File {

	A("a", 1),
	B("b", 2),
	C("c", 3),
	D("d", 4),
	E("e", 5),
	F("f", 6),
	G("g", 7),
	H("h", 8);

	private static final String NOT_FOUND_ERROR = "File는 a~h까지의 알파벳입니다.";

	private final String symbol;
	private final int value;

	File(final String symbol, final int value) {
		this.symbol = symbol;
		this.value = value;
	}

	public static File of(final String input) {
		return Arrays.stream(values())
				.filter(rank -> rank.symbol.equalsIgnoreCase(input))
				.findAny()
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ERROR));
	}

	public static File of(final int number) {
		return Arrays.stream(values())
				.filter(rank -> rank.value == number)
				.findAny()
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ERROR));
	}

	public Optional<File> add(final int number) {
		return Arrays.stream(values())
				.filter(rank -> rank.value == this.value + number)
				.findAny();
	}

	public int abs(final File file) {
		return Math.abs(this.value - file.value);
	}

	public int subtract(final File file) {
		return this.value - file.value;
	}
}
