package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Rank {

	EIGHT(8),
	SEVEN(7),
	SIX(6),
	FIVE(5),
	FOUR(4),
	THREE(3),
	TWO(2),
	ONE(1);

	private final int rank;

	Rank(final int rank) {
		this.rank = rank;
	}

	public static Rank from(final int value) {
		return Arrays.stream(Rank.values())
			.filter((rank) -> value == rank.rank)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public Rank setNextRank(final int unit) {
		return from((rank + unit));

	}

	public int rankValue() {
		return rank;
	}

	public static List<Rank> ranks() {
		final List<Rank> ranks = Arrays.asList(Rank.values());
		ranks.sort(Comparator.naturalOrder());
		return ranks;
	}
}
