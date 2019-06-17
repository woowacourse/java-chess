package chess.domain;

import java.util.Arrays;

public enum ChessXCoordinate {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private String symbol;
    private int index;

    ChessXCoordinate(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public ChessXCoordinate move(int n) {
        return valueOf(index + n);
    }

    public static ChessXCoordinate of(String symbol) {
       return Arrays.stream(values())
               .filter( coord -> coord.symbol.equals(symbol))
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 기호입니다."));
    }

    private static ChessXCoordinate valueOf(int index) {
        return Arrays.stream(values())
                .filter(coord -> coord.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 인덱스입니다: " + index));
    }

    public int getIndex() {
        return index;
    }

    public String getSymbol() {
        return symbol;
    }
}
