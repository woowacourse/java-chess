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

    public static Rank from(final int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Rank 값 입니다."));
    }

    public static Rank from(final String value) {
        return Rank.from(toRankValue(value));
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

    public Rank move(final int distance) {
        try {
            return from(value + distance);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

}
