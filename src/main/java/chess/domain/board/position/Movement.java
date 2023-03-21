package chess.domain.board.position;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public enum Movement {

    U(0, 1),
    D(0, -1),
    R(1, 0),
    L(-1, 0),

    UR(1, 1),
    UL(-1, 1),
    DR(1, -1),
    DL(-1, -1),

    UUR(1, 2),
    UUL(-1, 2),
    RRU(2, 1),
    RRD(2, -1),
    DDR(1, -2),
    DDL(-1, -2),
    LLU(-2, 1),
    LLD(-2, -1);

    private final int column;
    private final int row;

    Movement(final int column, final int row) {
        this.column = column;
        this.row = row;
    }

    public static Movement of(final int column, final int row) {
        return Arrays.stream(Movement.values())
                     .filter(isEqualsMovementFrom(column, row))
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 방향입니다."));
    }

    private static Predicate<Movement> isEqualsMovementFrom(final int column, final int row) {
        return movement -> movement.column == column && movement.row == row;
    }

    public Position nextPosition(Column column, Row row) {
        return new Position(column.value() + this.column, row.value() + this.row);
    }
}
