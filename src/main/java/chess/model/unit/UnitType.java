package chess.model.unit;

import java.util.Arrays;

public enum UnitType {
    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    PAWN("P");

    private static final String INVALID_ELEMENT_ERROR_MSG = "원하는 문자의 말이 없습니다.";

    private String symbol;

    UnitType(String symbol) {
        this.symbol = symbol;
    }

    public static UnitType findTypeWith(String symbol) {
        return Arrays.stream(UnitType.values())
                .filter(t -> t.symbol.equals(symbol))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ELEMENT_ERROR_MSG));
    }
}