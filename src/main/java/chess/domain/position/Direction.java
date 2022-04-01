package chess.domain.position;

import java.util.Arrays;

public enum Direction {
    VERTICAL_UP(0.0),
    VERTICAL_DOWN(180.0),
    HORIZONTAL_RIGHT(90.0),
    HORIZONTAL_LEFT(-90),
    DIAGONAL_RIGHT_UP(45.0),
    DIAGONAL_RIGHT_DOWN(135.0),
    DIAGONAL_LEFT_UP(-45.0),
    DIAGONAL_LEFT_DOWN(-135.0),
    SEVEN_SHAPE(0),
    UNDEFINED_DIRECTION(0);
    // TODO: degree가 VERTICAL_UP 과 겹치는 이슈

    private final double degree;

    Direction(double degree) {
        this.degree = degree;
    }

    public static Direction of(Position from, Position to) {
        double degree = Position.calculateDegree(from, to);

        if (isSevenShape(from, to)) {
            return SEVEN_SHAPE;
        }

        return Arrays.stream(values())
                .filter(direction -> direction.degree == degree)
                .findFirst()
                .orElse(UNDEFINED_DIRECTION);
    }

    private static boolean isSevenShape(Position from, Position to) {
        int deltaX = from.subtractXAxis(to);
        int deltaY = from.subtractYAxis(to);

        boolean isXTwoAndYOne = Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1;
        boolean osXOneAndYTwo = Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2;

        return isXTwoAndYOne || osXOneAndYTwo;
    }

    public boolean isVertical() {
        return equals(VERTICAL_UP) || equals(VERTICAL_DOWN);
    }

    public boolean isHorizontal() {
        return equals(HORIZONTAL_RIGHT) || equals(HORIZONTAL_LEFT);
    }

    public boolean isDiagonal() {
        return equals(DIAGONAL_RIGHT_UP) || equals(DIAGONAL_RIGHT_DOWN)
                || equals(DIAGONAL_LEFT_UP) || equals(DIAGONAL_LEFT_DOWN);
    }
}
