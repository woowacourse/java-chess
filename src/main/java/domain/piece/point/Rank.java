package domain.piece.point;

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

    public static Rank from(int value) {
        for (final Rank rank : Rank.values()) {
            if (rank.value == value) {
                return rank;
            }
        }
        throw new IllegalArgumentException(String.format("%d는 랭크에 존재하지 않습니다.", value));
    }
}
