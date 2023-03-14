package domain.board;

import java.util.Arrays;

public enum Rank {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private static final String INVALID_RANK = "행은 1 ~ 8까지만 가능합니다.";

    private String name;

    Rank(final String name) {
        this.name = name;
    }

    public static Rank from(final String rank) {
        return Arrays.stream(Rank.values())
                .filter(r -> r.name.equals(rank))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }
}
