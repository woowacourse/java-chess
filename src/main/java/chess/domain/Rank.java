package chess.domain;

import java.util.Arrays;

public enum Rank {

    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private final String value;

    Rank(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCoordinate() {
        return Integer.parseInt(value);
    }

    public static Rank findRank(int value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> Integer.parseInt(rank.value) == value)
            .findAny()
            .orElseThrow();
    }
}
