package chess.domain.move;

import chess.domain.position.Position;

import java.util.List;

public enum Direction {

    RIGHT(1, 0),
    LEFT(-1, 0),
    UP(0, 1),
    DOWN(0, -1),
    RIGHT_UP(1, 1),
    LEFT_UP(-1, 1),
    RIGHT_DOWN(1, -1),
    LEFT_DOWN(-1, -1),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_RIGHT_UP(2, 1),
    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_DOWN_RIGHT(1, -2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_LEFT_DOWN(-2, -1),
    KNIGHT_LEFT_UP(-2, 1);


    private final int dx;
    private final int dy;

    Direction(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction calculateUnitDirection(Position source, Position target) {
        int dx = diffFile(source, target);
        int dy = diffRank(source, target);

        if (dx == 0) {
            return calculateVertical(dx, dy);
        }
        if (dy == 0) {
            return calculateHorizontal(dx, dy);
        }
        if (Math.abs(dx) == Math.abs(dy)) {
            return calculateDiagonal(dx, dy);
        }
        if (Math.abs(dx * dy) == 2) {
            return calculateKnight(dx, dy);
        }
        throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
    }

    private static Direction calculateVertical(final int dx, final int dy) {
        if (dy < 0) {
            return DOWN;
        }
        return UP;
    }

    private static Direction calculateHorizontal(int dx, int dy) {
        if (dx < 0) {
            return LEFT;
        }
        return RIGHT;
    }

    private static Direction calculateDiagonal(final int dx, final int dy) {
        if (dx < 0 && dy > 0) {
            return LEFT_UP;
        }
        if (dx < 0 && dy < 0) {
            return LEFT_DOWN;
        }
        if (dx > 0 && dy > 0) {
            return RIGHT_UP;
        }
        return RIGHT_DOWN;
    }

    private static Direction calculateKnight(int dx, int dy) {
        final List<Direction> knightDirections = List.of(KNIGHT_RIGHT_UP, KNIGHT_RIGHT_DOWN, KNIGHT_LEFT_UP, KNIGHT_LEFT_DOWN, KNIGHT_UP_RIGHT, KNIGHT_UP_LEFT, KNIGHT_DOWN_RIGHT, KNIGHT_DOWN_LEFT);
        return knightDirections.stream()
                .filter(direction -> direction.dx == dx && direction.dy == dy)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
    }

    private static int diffFile(Position source, Position target) {
        return target.file() - source.file();
    }

    private static int diffRank(Position source, Position target) {
        return target.rank() - source.rank();
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
