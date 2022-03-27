package chess.domain.piece.move;

import chess.domain.board.Point;

import java.util.Arrays;
import java.util.List;

public enum StraightDirection implements Direction {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTHWEST(-1, 1),
    SOUTHWEST(-1, -1),
    NORTHEAST(1, 1),
    SOUTHEAST(1, -1),
    ;

    private final int dx;
    private final int dy;

    StraightDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static StraightDirection find(Point from, Point to) {
        int h = to.subtractHorizontal(from);
        int v = to.subtractVertical(from);
        int dx = toDegree(h);
        int dy = toDegree(v);
        return of(dx, dy);
    }

    private static int toDegree(int number) {
        if (number == 0) {
            return 0;
        }
        if (number > 0) {
            return 1;
        }
        return -1;
    }

    private static StraightDirection of(int dx, int dy) {
        return Arrays.stream(values())
                .filter(value -> value.dx == dx)
                .filter(value -> value.dy == dy)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 방향이 없습니다."));
    }

    @Override
    public int getDx() {
        return dx;
    }

    @Override
    public int getDy() {
        return dy;
    }

    public boolean isCross() {
        return List.of(NORTH, SOUTH, EAST, WEST).contains(this);
    }
}
