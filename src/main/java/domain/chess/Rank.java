package domain.chess;

import java.util.Arrays;

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
        return Arrays.stream(values())
                     .filter(rank -> rank.value == value)
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException(String.format("%d는 랭크에 존재하지 않습니다.", value)));
    }

    public boolean isUpEnd() {
        return ordinal() + 1 == values().length;
    }

    public boolean isDownEnd() {
        return ordinal() == 0;
    }

    public Rank moveUp(final int count) {
        if (canMoveUp(count)) {
            return values()[ordinal() + count];
        }
        throw new IllegalStateException(String.format("%d 만큼 위쪽으로 움직일 수 없습니다.", count));
    }

    public boolean canMoveUp(final int count) {
        return ordinal() + count < values().length;
    }

    public Rank moveDown(final int count) {
        if (canMoveDown(count)) {
            return values()[ordinal() - count];
        }
        throw new IllegalStateException(String.format("%d 만큼 아래쪽으로 움직일 수 없습니다.", count));
    }

    public boolean canMoveDown(final int count) {
        return ordinal() - count >= 0;
    }

}
