package chess.domain.position;

import chess.domain.piece.strategy.Direction;
import java.util.Arrays;

public enum Row {

    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2'),
    ONE('1');

    private final char value;

    Row(final char value) {
        this.value = value;
    }

    public Row move(final Direction direction) {
        char newValue = (char) (this.value + direction.rowValue());
        return of(newValue);
    }

    private Row of(final char value) {
        return Arrays.stream(values())
                .filter(row -> row.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("행 값이 범위를 벗어났습니다."))
                ;
    }

    public boolean canMove(final Direction direction) {
        return !goesLower(direction.rowValue()) && !goesHigher(direction.rowValue());
    }

    private boolean goesLower(final int rowValue) {
        return value + rowValue < ONE.value;
    }

    private boolean goesHigher(final int rowValue) {
        return value + rowValue > EIGHT.value;
    }

    public String value() {
        return String.valueOf(value);
    }
}
