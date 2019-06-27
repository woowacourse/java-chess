package chess.model;

import java.util.Arrays;

public enum Column {
    Col_1("1"),
    Col_2("2"),
    Col_3("3"),
    Col_4("4"),
    Col_5("5"),
    Col_6("6"),
    Col_7("7"),
    Col_8("8");

    private static final String INVALID_ELEMENT_ERROR_MSG = "원하는 방향의 행 좌표가 없습니다.";
    private static final String NOT_FOUND_SYMBOL_ERROR_MSG = "일치하는 SYMBOL을 가진 Column이 없습니다.";

    private String symbol;

    Column(final String symbol) {
        this.symbol = symbol;
    }

    boolean hasNext(Direction direction) {
        return this.calculateAscii() + direction.getColumnShiftUnit() >= Col_1.calculateAscii()
                && this.calculateAscii() + direction.getColumnShiftUnit() <= Col_8.calculateAscii();
    }

    Column next(Direction direction) {
        return Arrays.stream(Column.values())
                .filter(c -> this.calculateAscii() + direction.getColumnShiftUnit() == c.calculateAscii())
                .findAny().orElseThrow(() -> new InvalidElementException(INVALID_ELEMENT_ERROR_MSG));
    }

    static Column findBySymbol(String symbol) {
        return Arrays.stream(Column.values())
                .filter(column -> column.symbol.equals(symbol))
                .findAny()
                .orElseThrow(() -> new InvalidElementException(NOT_FOUND_SYMBOL_ERROR_MSG));
    }

    private int calculateAscii() {
        return (int) symbol.charAt(0);
    }

    @Override
    public String toString() {
        return symbol;
    }
}