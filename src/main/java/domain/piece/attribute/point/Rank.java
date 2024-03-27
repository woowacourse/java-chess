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
    private final int value;

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

    public boolean isTop() {
        return ordinal() == values().length-1;
    }

    public boolean isBottom() {
        return ordinal() == 0;
    }

    public boolean canMoveUp(final int step) {
        return ordinal() + step < values().length;
    }

    public boolean canMoveDown(final int step) {
        return ordinal() - step >= 0;
    }

    public Rank moveUp(final int step) {
        if (canMoveUp(step)) {
            return values()[ordinal() + step];
        }
        throw new IllegalStateException("이동할 수 없는 위치입니다.");
    }

    public Rank moveDown(final int step) {
        if (canMoveDown(step)) {
            return values()[ordinal() - step];
        }
        throw new IllegalStateException("이동할 수 없는 위치입니다.");
    }
}
