package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),

    NW(-1, 1),
    NE(1, 1),
    SW(-1, -1),
    SE(1, -1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction of(int x, int y) {
        return Arrays.stream(values())
            .filter(direction -> direction.x == x && direction.y == y)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 방향입니다."));
    }

    public static Direction ofDiagonal(int x, int y) {
        if (Math.abs(x) - Math.abs(y) != 0) {
            throw new IllegalArgumentException("대각선 방향이 아닙니다.");
        }
        return of(Integer.compare(x, 0), Integer.compare(y, 0));
    }

    public static Direction ofLinear(int x, int y) {
        if (Math.abs(x - y) != Math.abs(x + y)) {
            throw new IllegalArgumentException("직선 방향이 아닙니다.");
        }
        return of(Integer.compare(x, 0), Integer.compare(y, 0));
    }

    public static Direction ofEvery(int x, int y) {
        if (Math.abs(x) - Math.abs(y) != 0 && Math.abs(x - y) != Math.abs(x + y)) {
            throw new IllegalArgumentException("8방향이 아닙니다.");
        }
        return of(Integer.compare(x, 0), Integer.compare(y, 0));
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NE, NW, SE, SW);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NE, NW, SE, SW);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NE, NW, NORTH);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SE, SW, SOUTH);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
