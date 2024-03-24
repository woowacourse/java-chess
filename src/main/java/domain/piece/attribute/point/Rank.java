package domain.piece.attribute.point;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);
    private int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank from(final int value) {
        for (final var rank : Rank.values()) {
            if (rank.value == value) {
                return rank;
            }
        }
        throw new IllegalArgumentException(String.format("%d는 랭크에 존재하지 않습니다.", value));
    }

    public static boolean isInBoundary(final int index) {
        return index >= 0 && index < values().length;
    }

    public static Rank findByIndex(final int ordinalIndex) {
        return values()[ordinalIndex];
    }

}
