package chess.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static List<Rank> orderedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingInt(col -> col.value))
                .collect(Collectors.toList());
    }

    public static List<Rank> reverseOrderedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.<Rank>comparingInt(col -> col.value).reversed())
                .collect(Collectors.toList());
    }

    public boolean isDownward(Rank destination) {
        return value > destination.value;
    }

    public boolean isUpward(Rank destination) {
        return value < destination.value;
    }

    public List<Rank> getPath(Rank to) {
        int start = Math.min(this.value, to.value);
        int end = Math.max(this.value, to.value);

        if (this.value < to.value) {
            return getPath(orderedValues(), start, end);
        }

        return getPath(reverseOrderedValues(), start, end);
    }

    private List<Rank> getPath(List<Rank> orderedRanks, int start, int end) {
        return orderedRanks.stream()
                .filter(rank -> start <= rank.value && rank.value <= end)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static int distanceBetween(Rank from, Rank to) {
        return Math.abs(from.value - to.value);
    }
}
