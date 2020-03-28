package chess.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
	ONE("1", 1),
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8);

	private static final String ILLEGAL_RANK_NAME_EXCEPTION_MESSAGE = "올바른 행값이 아닙니다.";
	private static final int MINIMUM_DISTANCE = 1;

	private final String name;
	private final int number;

	Rank(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public static List<Rank> valuesBetween(Rank start, Rank end) {
		if (start.getNumber() > end.getNumber()) {
			return Arrays.stream(values())
				.sorted(Comparator.reverseOrder())
				.filter(rank -> rank.getNumber() < start.getNumber() && rank.getNumber() > end.getNumber())
				.collect(Collectors.toList());
		}
		return Arrays.stream(values())
			.filter(rank -> rank.getNumber() > start.getNumber() && rank.getNumber() < end.getNumber())
			.collect(Collectors.toList());
	}

	public static Rank of(String name) {
		return Arrays.stream(values())
			.filter(value -> value.name.equals(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_RANK_NAME_EXCEPTION_MESSAGE));
	}

	public static List<Rank> valuesReverseOrder() {
		return Arrays.stream(values())
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList());
	}

	public String getName() {
		return this.name;
	}

	public int getNumber() {
		return this.number;
	}

	public boolean isNear(Rank other) {
		return Math.abs(this.getNumber() - other.getNumber()) <= MINIMUM_DISTANCE;
	}

	public int findDifference(Rank other) {
		return Math.abs(this.getNumber() - other.getNumber());
	}
}
