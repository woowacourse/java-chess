package chess.model.unit;

import chess.model.InvalidElementException;

import java.util.Arrays;

public enum Side {
    BLACK("b"),
    WHITE("w");

    private static final String NOT_FOUND_ELEMENT_ERROR_MSG = "해당 SYMBOL의 색깔을 가져올 수 없습니다.";

    private String symbol;

    Side(String symbol) {
        this.symbol = symbol;
    }

    public static Side findSide(String side) {
        return Arrays.stream(Side.values())
                .filter(s -> s.symbol.equals(side))
                .findFirst()
                .orElseThrow(() -> new InvalidElementException(NOT_FOUND_ELEMENT_ERROR_MSG));
    }

    public String getSymbol() {
        return symbol;
    }
}