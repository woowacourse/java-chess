package chess.domain.chessPiece.position;

import java.util.Arrays;

public enum Rank {
	EIGHT(8),
	SEVEN(7),
	SIX(6),
	FIVE(5),
	FOUR(4),
	THREE(3),
	TWO(2),
	ONE(1);

	private final int number;

	Rank(int number) {
		this.number = number;
	}

	public static Rank of(String rankValue) {
		return of(Integer.parseInt(rankValue));
	}

	public static Rank of(int number) {
		return Arrays.stream(Rank.values())
				.filter(x -> x.number == number)
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}

	public int getNumber() {
		return number;
	}

	public int calculateDistance(Rank rank) {
		return this.number - rank.number;
	}
}
