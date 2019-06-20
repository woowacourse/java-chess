package chess.domain.direction.core;

import java.util.Arrays;

public enum Direction {
    UP(Square.of(0, -1)),
    DOWN(Square.of(0, 1)),
    LEFT(Square.of(-1, 0)),
    RIGHT(Square.of(1, 0)),
    UP_LEFT(Square.of(-1, -1)),
    UP_RIGHT(Square.of(1, -1)),
    DOWN_LEFT(Square.of(-1, 1)),
    DOWN_RIGHT(Square.of(1, 1)),
    DOUBLE_UP_LEFT(Square.of(-1, -2)),
    DOUBLE_UP_RIGHT(Square.of(1, -2)),
    DOUBLE_DOWN_LEFT(Square.of(-1, 2)),
    DOUBLE_DOWN_RIGHT(Square.of(1, 2)),
    DOUBLE_LEFT_UP(Square.of(-2, -1)),
    DOUBLE_LEFT_DOWN(Square.of(-2, 1)),
    DOUBLE_RIGHT_UP(Square.of(2, -1)),
    DOUBLE_RIGHT_DOWN(Square.of(2, 1));

    Square unit;

    Direction(Square unit) {
        this.unit = unit;
    }

    public Square move(Square square) {
        return Square.of(square.getX() + unit.getX(), square.getY() + unit.getY());
    }

    public static Direction valuesOf(Square source, Square target) {
        return Arrays.stream(values())
                .filter(e -> e.checkDirection(source, target))
                .findAny()
                .orElse(null);
    }

    public boolean checkDirection(Square source, Square target) {
        int vectorX = target.getX() - source.getX();
        int vectorY = target.getY() - source.getY();

        double scalar = unit.getX() != 0 ? vectorX * 1.0 / unit.getX() : vectorY * 1.0 / unit.getY();
        return scalar < 0 ? false : (scalar * unit.getX() == vectorX) && (scalar * unit.getY() == vectorY);
    }

    public double calculateStep(Square source, Square target) {
        int vectorX = target.getX() - source.getX();
        int vectorY = target.getY() - source.getY();

        return unit.getX() != 0 ? vectorX * 1.0 / unit.getX() : vectorY * 1.0 / unit.getY();
    }
}
