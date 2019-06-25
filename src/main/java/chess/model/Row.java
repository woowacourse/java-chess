package chess.model;

import java.util.Arrays;

public enum Row {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private static final String INVALID_ELEMENT_ERROR_MSG = "원하는 방향의 행 좌표가 없습니다.";

    private String symbol;

    Row(final String symbol) {
        this.symbol = symbol;
    }

    boolean hasNext(Direction direction) {
        return this.calculateAscii() + direction.getRowShiftUnit() >= A.calculateAscii()
                && this.calculateAscii() + direction.getRowShiftUnit() <= H.calculateAscii();
    }

    Row next(Direction direction) {
        return Arrays.stream(Row.values())
                .filter(r -> this.calculateAscii() + direction.getRowShiftUnit() == r.calculateAscii())
                .findAny().orElseThrow(() -> new InvalidElementException(INVALID_ELEMENT_ERROR_MSG));
    }

    private int calculateAscii() {
        return (int) symbol.charAt(0);
    }

    @Override
    public String toString() {
        return symbol;
    }
}