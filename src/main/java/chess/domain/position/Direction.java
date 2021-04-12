package chess.domain.position;

import chess.domain.piece.attribute.Color;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private int xDegree;
    private int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static Direction of(int xDegree, int yDegree) {
        int divisor = calculateDivisor(Math.abs(xDegree), Math.abs(yDegree));

        return findDirection(xDegree / divisor, yDegree / divisor);
    }

    public static Direction of(Position from, Position to) {
        return of(from.calculateXDegree(to), from.calculateYDegree(to));
    }

    private static int calculateDivisor(int xDegree, int yDegree) {
        if (xDegree == 0) {
            return yDegree;
        }
        if (yDegree == 0) {
            return xDegree;
        }
        return calculateGreatestCommonDivisor(Math.max(xDegree, yDegree), Math.min(xDegree, yDegree));
    }

    private static int calculateGreatestCommonDivisor(int bigger, int smaller) {
        while (smaller > 0) {
            int temporalNumber = bigger;
            bigger = smaller;
            smaller = temporalNumber % smaller;
        }
        return bigger;
    }

    private static Direction findDirection(int xDegree, int yDegree) {
        return Arrays.stream(values())
                .filter(direction -> direction.xDegree == xDegree && direction.yDegree == yDegree)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 방향입니다."));
    }

    public static boolean isNorthDiagonal(Direction direction) {
        return Arrays.asList(NORTHEAST, NORTHWEST).contains(direction);
    }

    public static boolean isSouthDiagonal(Direction direction) {
        return Arrays.asList(SOUTHWEST, SOUTHEAST).contains(direction);
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> pawnDirection(Color color) {
        if (color == Color.WHITE) {
            return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
        }
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }
}