package chess.domain.position;

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

    private final int yPosition;

    YPosition(final int yPosition) {
        this.yPosition = yPosition;
    }

    public static YPosition of(final int yPosition) {
        return Arrays.stream(values())
                .filter(value -> value.yPosition == yPosition)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 세로축이 잘못 입력되었습니다."));
    }

    public static YPosition of(final String symbol) {
        return Arrays.stream(values())
                .filter(value -> value.yPosition == Integer.parseInt(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 세로축이 잘못 입력되었습니다."));
    }

    public static boolean checkRange(final int yPosition) {
        return ONE.getYPosition() <= yPosition && yPosition <= EIGHT.getYPosition();
    }

    public int getYPosition() {
        return yPosition;
    }

    @Override
    public String toString() {
        return "YPosition{" + "y=" + yPosition + '}';
    }
}
