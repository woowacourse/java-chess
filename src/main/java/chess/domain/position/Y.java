package chess.domain.position;

import java.util.Arrays;

/**
 * 체스의 Rank 에 해당
 */
public enum Y {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private int y;

    Y(int y) {
        this.y = y;
    }

    public static Y of(int y) {
        return Arrays.stream(values())
            .filter(x -> x.y == y)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("체스의 Y 축에 존재하지 않는 Rank입니다."));
    }

    public int getInt() {
        return this.y;
    }

    @Override
    public String toString() {
        return "" + y;
    }
}
