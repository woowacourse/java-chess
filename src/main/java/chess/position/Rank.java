package chess.position;

import java.util.ArrayList;
import java.util.Arrays;
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

    private static final int MINIMUM_DISTANCE = 1;

    private final String name;
    private final int number;

    Rank(String name, int number) {
        this.name = name;
        this.number = number;
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

    public static List<Rank> valuesBetween(Rank start, Rank end) {
        int bigger = Math.max(start.getNumber(), end.getNumber());
        int smaller = Math.min(start.getNumber(), end.getNumber());
        return Arrays.stream(values())
                .filter(rank -> rank.getNumber() > smaller)
                .filter(rank -> rank.getNumber() < bigger)
                .collect(Collectors.toList());
    }

    public static List<Rank> valuesRangeClosed(Rank start, Rank end) {
        int bigger = Math.max(start.getNumber(), end.getNumber());
        int smaller = Math.min(start.getNumber(), end.getNumber());
        return Arrays.stream(values())
                .filter(rank -> rank.getNumber() >= smaller)
                .filter(rank -> rank.getNumber() <= bigger)
                .collect(Collectors.toList());
    }

    public boolean isNear(Rank other) {
        return Math.abs(this.getNumber() - other.getNumber()) <= MINIMUM_DISTANCE;
    }

    public int findDifference(Rank other) {
        return Math.abs(this.getNumber() - other.getNumber());
    }

    public Rank addRank(int number) {
        return of(this.number + number);
    }

    public boolean isNone() {
        return this == NONE;
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return this.number;
    }
}
