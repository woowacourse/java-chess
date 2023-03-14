package domain.board;

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

    public String getName() {
        return name;
    }
}
