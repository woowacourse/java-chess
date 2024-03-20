package chess.domain.chessboard.attribute;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Direction {
    UP(1, 0),
    UP_LEFT(1, -1),
    UP_RIGHT(1, 1),
    DOWN(-1, 0),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1)
    ;

    private final int row;
    private final int column;

    Direction(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public static Set<Direction> all() {
        return Arrays.stream(values())
                .collect(Collectors.toUnmodifiableSet());
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
