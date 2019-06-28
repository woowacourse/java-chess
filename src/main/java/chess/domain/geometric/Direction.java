package chess.domain.geometric;

import chess.domain.chess.exception.IllegalDirectionException;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    EAST(Vector.of(1, 0)),
    NORTHEAST(Vector.of(1, 1)),
    NORTH(Vector.of(0, 1)),
    NORTHWEST(Vector.of(-1, 1)),
    WEST(Vector.of(-1, 0)),
    SOUTHWEST(Vector.of(-1, -1)),
    SOUTH(Vector.of(0, -1)),
    SOUTHEAST(Vector.of(1, -1));

    private Vector vector;

    Direction(Vector vector) {
        this.vector = vector;
    }

    public static Direction of(Vector vector) throws IllegalDirectionException {
        for (Direction direction : values()) {
            if (direction.isParallelTo(vector)) {
                return direction;
            }
        }
        throw new IllegalDirectionException("올바른 방향이 아닙니다.(동서남북 및 대각선 방향이 아님)");
    }

    public static List<Direction> plusShape() {
        return Arrays.asList(EAST, NORTH, WEST, SOUTH);
    }

    public static List<Direction> multiplierShape() {
        return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST);
    }

    public static List<Direction> southDirections() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> northDirections() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> northDiagonal() {
        return Arrays.asList(NORTHEAST, NORTHWEST);
    }

    public static List<Direction> southDiagonal() {
        return Arrays.asList(SOUTHEAST, SOUTHWEST);
    }

    public boolean isParallelTo(Vector another) {
        return this.vector.isParallelTo(another);
    }

    public boolean isEqualTo(Vector vector) {
        return this.vector.equals(vector);
    }

    public Position apply(Position position) {
        int x = position.getX() + vector.getX();
        int y = position.getY() + vector.getY();
        return Position.create(x, y);
    }
}
