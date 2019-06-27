package chess.model.unit;

import java.util.Arrays;

public enum UnitType {
    KING("K", 0.0),
    QUEEN("Q", 9.0),
    ROOK("R", 5.0),
    KNIGHT("N", 2.5),
    BISHOP("Row_B", 3.0),
    PAWN("P", 1.0);

    private static final String INVALID_ELEMENT_ERROR_MSG = "원하는 문자의 말이 없습니다.";

    private String symbol;
    private double score;

    UnitType(String symbol, double score) {
        this.symbol = symbol;
        this.score = score;
    }

    public static UnitType findTypeWith(String symbol) {
        return Arrays.stream(UnitType.values())
                .filter(t -> t.symbol.equals(symbol))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ELEMENT_ERROR_MSG));
    }

    public String getSymbol() {
        return symbol;
    }

    public double getScore() {
        return score;
    }
}