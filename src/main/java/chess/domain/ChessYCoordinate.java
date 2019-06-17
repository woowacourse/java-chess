package chess.domain;

import java.util.Arrays;

public enum ChessYCoordinate {
    RANK_1("1", 7),
    RANK_2("2", 6),
    RANK_3("3", 5),
    RANK_4("4", 4),
    RANK_5("5", 3),
    RANK_6("6", 2),
    RANK_7("7", 1),
    RANK_8("8", 0);

    private String symbol;
    private int index;

    ChessYCoordinate(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public ChessYCoordinate move(int n) {
        return valueOf(index - n);
    }

    public static ChessYCoordinate valueOf(int index) {
        return Arrays.stream(values())
                .filter(coord -> coord.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 인덱스입니다: " + index));
    }

    public static ChessYCoordinate of(String symbol) {
        return Arrays.stream(values())
                .filter(coord -> coord.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 기호입니다."));
    }

    public String getSymbol() {
        return symbol;
    }

    public int getIndex() {
        return index;
    }
}
