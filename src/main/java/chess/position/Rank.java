package chess.position;

import java.util.ArrayList;
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
    EIGHT("8", 8),
    NONE("", 0);

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

    public static Rank of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(NONE);
    }

    public static Rank of(int number) {
        return Arrays.stream(values())
                .filter(value -> value.number == number)
                .findFirst()
                .orElse(NONE);
    }

    public static Rank[] valuesExceptNone() {
        List<Rank> valuesExceptNone = new ArrayList(Arrays.asList(values()));
        valuesExceptNone.remove(NONE);
        return valuesExceptNone.toArray(new Rank[0]);
    }

    public Rank[] valuesWithDifferenceBelow(int distance) {
        return Arrays.stream(values())
                .filter(rank -> findDifference(rank) <= distance)
                .toArray(Rank[]::new);
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

    public Rank getRankUsingIncreaseAmount(int increaseAmountOfRank) {
        return of(number + increaseAmountOfRank);
    }

    public boolean isNone() {
        return this == NONE;
    }
}
