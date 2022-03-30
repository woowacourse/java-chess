package chess.controller.converter;

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

	private static final String INVALID_RANK_INPUT = "Rank는 1~8 사이에서 입력해야 합니다.";

	private final String name;
	private final int row;

	Rank(String name, int row) {
		this.name = name;
		this.row = row;
	}

	static Rank from(String name) {
		return Arrays.stream(values())
			.filter(rank -> rank.name.equals(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_INPUT));
	}

	public static List<Rank> reverseValues() {
		return Arrays.stream(values())
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList());
	}

	public int getRow() {
		return row;
	}

	public String getName() {
		return name;
	}
}
