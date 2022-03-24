package chess.position;

import java.util.*;
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
            .sorted(Comparator.<Rank>comparingInt(col -> col.value).reversed())
            .collect(Collectors.toList());
    }

    public boolean isDownward(Rank destination) {
        return value > destination.value;
    }

    public boolean isUpward(Rank destination) {
        return value < destination.value;
    }

    public int getDistance(Rank rank) {
        return Math.abs(this.value - rank.value);
    }
}
