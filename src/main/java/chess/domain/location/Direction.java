package chess.domain.location;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),

    UUR(1, 2),
    UUL(-1, 2),
    URR(2, 1),
    ULL(-2, 1),
    DDR(1, -2),
    DDL(-1, -2),
    DRR(2, -1),
    DLL(-2, -1);

    private final int column;
    private final int row;

    Direction(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public static List<Direction> orthogonal() {
        return Arrays.asList(UP, DOWN, RIGHT, LEFT);
    }

    public static List<Direction> diagonal() {
        return Arrays.asList(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> octilinear() {
        return Arrays.asList(UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> knight() {
        return Arrays.asList(UUR, UUL, URR, ULL, DDR, DDL, DRR, DLL);
    }

    public int column() {
        return column;
    }

    public int row() {
        return row;
    }
}
