package domain.position;

import java.util.Arrays;

public enum YPosition {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int y;

    YPosition(final int y) {
        this.y = y;
    }

    public static YPosition of(final int y) {
        return Arrays.stream(values())
                .filter(value -> value.y == y)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 세로축이 잘못 입력되었습니다."));
    }

    public static YPosition of(final String symbol) {
        return Arrays.stream(values())
                .filter(value -> value.y == Integer.parseInt(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 세로축이 잘못 입력되었습니다."));
    }

    public static boolean checkRange(final int y) {
        return ONE.getY() <= y && y <= EIGHT.getY();
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "YPosition{" + "y=" + y + '}';
    }
}
