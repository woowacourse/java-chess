package chess.domain.position;

import java.util.Arrays;

public enum XPosition {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String symbol;
    private final int xPosition;

    XPosition(final String symbol, final int xPosition) {
        this.symbol = symbol;
        this.xPosition = xPosition;
    }

    public static XPosition of(final int xPosition) {
        return Arrays.stream(values())
                .filter(value -> value.xPosition == xPosition)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 가로축이 잘못 입력되었습니다."));
    }

    public static XPosition of(final String symbol) {
        return Arrays.stream(values())
                .filter(value -> value.symbol.equals(symbol.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 가로축이 잘못 입력되었습니다."));
    }

    public static boolean checkRange(final int xPosition) {
        return A.getXPosition() <= xPosition && xPosition <= H.getXPosition();

    }

    public int getXPosition() {
        return xPosition;
    }

    public String getSymbol() {
        return symbol.toUpperCase();
    }

    @Override
    public String toString() {
        return "XPosition{" + "x=" + xPosition + '}';
    }
}
