package chess.domain.chessboard.attribute;

import java.util.Arrays;
import java.util.List;
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

    public static Set<Set<Direction>> ofKnight() {
        return Set.of(
                Set.of(UP, UP_LEFT), Set.of(UP, UP_RIGHT),
                Set.of(RIGHT, UP_RIGHT), Set.of(RIGHT, DOWN_RIGHT),
                Set.of(DOWN, DOWN_RIGHT), Set.of(DOWN, DOWN_LEFT),
                Set.of(LEFT, DOWN_LEFT), Set.of(LEFT, UP_LEFT)
        );
    }

    public static Set<List<Direction>> ofStartingPawn() {
        return Set.of(
                List.of(UP),
                List.of(UP_LEFT),
                List.of(UP_RIGHT),
                List.of(UP, UP)
        );
    }

    public static Set<Direction> ofPawn() {
        return Set.of(UP, UP_LEFT, UP_RIGHT);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
