package chess.domain.position;

import chess.domain.piece.strategy.Direction;
import java.util.Arrays;

public enum Column {

    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char value;

    Column(final char value) {
        this.value = value;
    }

    public Column move(final Direction direction) {
        char newValue = (char) (this.value + direction.columnValue());
        return of(newValue);
    }

    private Column of(final char value) {
        return Arrays.stream(values())
                .filter(column -> column.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("열 값이 범위를 벗어났습니다."))
                ;
    }

    public boolean canMove(final Direction direction) {
        return !goesLower(direction.columnValue()) && !goesHigher(direction.columnValue());
    }

    private boolean goesLower(final int columnValue) {
        return value + columnValue < A.value;
    }

    private boolean goesHigher(final int columnValue) {
        return value + columnValue > H.value;
    }

    public String value() {
        return String.valueOf(value);
    }
}
