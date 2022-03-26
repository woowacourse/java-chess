package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank of(final int value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.value == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Rank 값 입니다."));
    }

    public static Rank of(final String value) {
        return Rank.of(toRankValue(value));
    }

    private static int toRankValue(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 Rank 값 입니다.");
        }
    }

    public int calculateDistance(final Rank rank) {
        return value - rank.value;
    }

    public Rank plus() {
        try {
            return of(value + 1);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public Rank minus() {
        try {
            return of(value - 1);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}
