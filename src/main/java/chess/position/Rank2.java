package chess.position;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rank2 implements Comparable<Rank2> {
	private static final Map<Integer, Rank2> RANK_CACHE = new LinkedHashMap<>();
	static {
		for (int i = 8; i >= 1; i--) {
			RANK_CACHE.put(i, new Rank2(String.valueOf(i), i));
		}
	}

	private static final String ILLEGAL_RANK_NAME_EXCEPTION_MESSAGE = "올바른 행값이 아닙니다.";
	private static final int MINIMUM_DISTANCE = 1;

	private final String name;
	private final int number;

	private Rank2(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public static List<Rank2> valuesBetween(Rank2 start, Rank2 end) {
		if (start.getNumber() > end.getNumber()) {
			return RANK_CACHE.values().stream()
				.sorted(reverseOrder())
				.filter(rank -> rank.getNumber() < start.getNumber() && rank.getNumber() > end.getNumber())
				.collect(toList());
		}
		return RANK_CACHE.values().stream()
			.filter(rank -> rank.getNumber() > start.getNumber() && rank.getNumber() < end.getNumber())
			.collect(toList());
	}

	public static Rank2 of(String name) {
		return of(Integer.parseInt(name));
	}

	private static Rank2 of(int name) {
		if (RANK_CACHE.containsKey(name)) {
			return RANK_CACHE.get(name);
		}
		throw new IllegalArgumentException(ILLEGAL_RANK_NAME_EXCEPTION_MESSAGE);
	}

	public static List<Rank2> valuesReverseOrder() {
		return RANK_CACHE.values().stream()
			.sorted(reverseOrder())
			.collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}

	public String getName() {
		return this.name;
	}

	public int getNumber() {
		return this.number;
	}

	public boolean isNear(Rank2 other) {
		return Math.abs(this.getNumber() - other.getNumber()) <= MINIMUM_DISTANCE;
	}

	public int findDifference(Rank2 other) {
		return Math.abs(this.getNumber() - other.getNumber());
	}

	@Override
	public int compareTo(Rank2 o) {
		return Integer.compare(this.number, o.number);
	}
}
