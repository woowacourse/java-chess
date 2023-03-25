package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
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

	private final int value;

	Rank(final int value) {
		this.value = value;
	}

	public static Rank from(final int value) {
		return Arrays.stream(Rank.values())
			.filter((rank) -> value == rank.value)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public static List<Rank> ranks() {
		return new ArrayList<>(List.of(values()));
	}

	public Rank nextRank(final int unit) {
		return from((value + unit));
	}

	public int value() {
		return value;
	}
}
