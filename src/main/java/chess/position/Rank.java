package chess.position;

import java.util.ArrayList;
import java.util.List;

public enum Rank {
	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8");

	private final String name;

	Rank(String name) {
		this.name = name;
	}

	public static List<Rank> valuesBetween(Rank start, Rank end) {
		Rank smaller = min(start, end);
		Rank bigger = max(start, end);

		List<Rank> ranks = new ArrayList<>();
		for (Rank rank : values()) {
			if (between(rank, smaller, bigger)) {
				ranks.add(rank);
			}
		}
		ranks.remove(start);
		return ranks;
	}

	private static boolean between(Rank target, Rank smaller, Rank bigger) {
		return target.compareTo(smaller) >= 0 && target.compareTo(bigger) <= 0;
	}

	private static Rank max(Rank rank1, Rank rank2) {
		if (rank1.compareTo(rank2) > 0) {
			return rank1;
		} else {
			return rank2;
		}
	}

	private static Rank min(Rank rank1, Rank rank2) {
		if (rank1.compareTo(rank2) < 0) {
			return rank1;
		} else {
			return rank2;
		}
	}

	public String getName() {
		return name;
	}
}
