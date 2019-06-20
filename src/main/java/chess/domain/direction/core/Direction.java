package chess.domain.direction.core;

import chess.domain.piece.core.Square;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public enum Direction {
    UP(new Square(0, -1), (x, y) -> new Square(x, y - 1)),
    DOWN(new Square(0, 1), (x, y) -> new Square(x, y + 1)),
    LEFT(new Square(-1, 0), (x, y) -> new Square(x - 1, y)),
    RIGHT(new Square(1, 0), (x, y) -> new Square(x + 1, y)),
    UP_LEFT(new Square(-1, -1), (x, y) -> new Square(x - 1, y - 1)),
    UP_RIGHT(new Square(1, -1), (x, y) -> new Square(x + 1, y - 1)),
    DOWN_LEFT(new Square(-1, 1), (x, y) -> new Square(x - 1, y + 1)),
    DOWN_RIGHT(new Square(1, 1), (x, y) -> new Square(x + 1, y + 1)),
    DOUBLE_UP_LEFT(new Square(-1, -2), (x, y) -> new Square(x - 1, y - 2)),
    DOUBLE_UP_RIGHT(new Square(1, -2), (x, y) -> new Square(x + 1, y - 2)),
    DOUBLE_DOWN_LEFT(new Square(-1, 2), (x, y) -> new Square(x - 1, y + 2)),
    DOUBLE_DOWN_RIGHT(new Square(1, 2), (x, y) -> new Square(x + 1, y + 2)),
    DOUBLE_LEFT_UP(new Square(-2, -1), (x, y) -> new Square(x - 2, y - 1)),
    DOUBLE_LEFT_DOWN(new Square(-2, 1), (x, y) -> new Square(x - 2, y + 1)),
    DOUBLE_RIGHT_UP(new Square(2, -1), (x, y) -> new Square(x + 2, y - 1)),
    DOUBLE_RIGHT_DOWN(new Square(2, 1), (x, y) -> new Square(x + 2, y + 1));

    Square unit;
    Function<Square, Square> moveStrategy;
    BiFunction<Integer, Integer, Square> strategy;

    Direction(Square unit, BiFunction<Integer, Integer, Square> strategy) {
        this.strategy = strategy;
        this.unit = unit;
    }

    public Square move(Square square) {
        return strategy.apply(square.getX(), square.getY());
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
