package chess.domain.board;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public enum Rank {

	ONE("1", 1),
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8);

	private static final String NOT_FOUND_ERROR = "Rank는 1~8까지의 숫자입니다.";

	private final String symbol;
	private final int value;

	Rank(final String symbol, final int value) {
		this.symbol = symbol;
		this.value = value;
	}

	public static Rank of(final String input) {
		return Arrays.stream(values())
				.filter(rank -> rank.symbol.equalsIgnoreCase(input))
				.findAny()
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ERROR));
	}

	public static Rank of(final int number) {
		return Arrays.stream(values())
				.filter(rank -> rank.value == number)
				.findAny()
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ERROR));
	}

	public Optional<Rank> add(final int number) {
		return Arrays.stream(values())
				.filter(rank -> rank.value == this.value + number)
				.findAny();
	}

	public int abs(final Rank rank) {
		return Math.abs(this.value - rank.value);
	}

	public int subtract(final Rank rank) {
		return this.value - rank.value;
	}

	public String getSymbol() {
		return symbol;
	}
}
