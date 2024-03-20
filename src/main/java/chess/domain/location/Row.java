package chess.domain.location;

import chess.domain.board.Direction;
import java.util.Arrays;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private static final Row[] ROWS = new Row[]
            {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT};
    private final int index;

    Row(int index) {
        this.index = index;
    }

    public static Row of(String input) {
        return Arrays.stream(values())
                .filter(row -> row.isNumber(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Row 입력입니다."));
    }

    public Row move(Direction direction) {
        if (direction.isUpSide()) {
            return this.next();
        }
        if (direction.isDownside()) {
            return this.previous();
        }
        return this;
    }

    private Row previous() {
        int ordinalIndex = this.index - 1;
        try {
            return ROWS[ordinalIndex - 1];
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("잘못된 방향 입력입니다.");
        }
    }

    private Row next() {
        int ordinalIndex = this.index - 1;
        try {
            return ROWS[ordinalIndex + 1];
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("잘못된 방향 입력입니다.");
        }
    }
    //TODO 네이밍 고민

    private boolean isNumber(String input) {
        return index == Integer.parseInt(input);
    }

    public int calculateDistance(Row other) {
        return other.index - this.index;
    }
}
