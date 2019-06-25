package chess.model;

import java.util.Arrays;

public enum Column {
    _1("1"),
    _2("2"),
    _3("3"),
    _4("4"),
    _5("5"),
    _6("6"),
    _7("7"),
    _8("8");

    private static final String INVALID_ELEMENT_ERROR_MSG = "원하는 방향의 행 좌표가 없습니다.";

    private String symbol;

    Column(final String symbol) {
        this.symbol = symbol;
    }

    boolean hasNext(Direction direction) {
        return this.calculateAscii() + direction.getColumnShiftUnit() >= _1.calculateAscii()
                && this.calculateAscii() + direction.getColumnShiftUnit() <= _8.calculateAscii();
    }

    Column next(Direction direction) {
        return Arrays.stream(Column.values())
                .filter(c -> this.calculateAscii() + direction.getColumnShiftUnit() == c.calculateAscii())
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