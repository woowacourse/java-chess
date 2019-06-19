package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public enum Direction {
    UP((x, y) -> new Square(x, y - 1),
            (source, target) -> source.getX() == target.getX() && source.getY() > target.getY()),
    DOWN((x, y) -> new Square(x, y + 1),
            (source, target) -> source.getX() == target.getX() && source.getY() < target.getY()),
    LEFT((x, y) -> new Square(x - 1, y),
            (source, target) -> source.getX() > target.getX() && source.getY() == target.getY()),
    RIGHT((x, y) -> new Square(x + 1, y),
            (source, target) -> source.getX() < target.getX() && source.getY() == target.getY()),
    UP_LEFT((x, y) -> new Square(x - 1, y - 1),
            (source, target) -> checkDirection(source, target, new Square(-1, -1))),
    UP_RIGHT((x, y) -> new Square(x + 1, y - 1),
            (source, target) -> checkDirection(source, target, new Square(1, -1))),
    DOWN_LEFT((x, y) -> new Square(x - 1, y + 1),
            (source, target) -> checkDirection(source, target, new Square(-1, 1))),
    DOWN_RIGHT((x, y) -> new Square(x + 1, y + 1),
            (source, target) -> checkDirection(source, target, new Square(1, 1))),
    DOUBLE_UP_LEFT((x, y) -> new Square(x - 1, y - 2),
            (source, target) -> checkDirection(source, target, new Square(-1, -2))),
    DOUBLE_UP_RIGHT((x, y) -> new Square(x + 1, y - 2),
            (source, target) -> checkDirection(source, target, new Square(1, -2))),
    DOUBLE_DOWN_LEFT((x, y) -> new Square(x - 1, y + 2),
            (source, target) -> checkDirection(source, target, new Square(-1, 2))),
    DOUBLE_DOWN_RIGHT((x, y) -> new Square(x + 1, y + 2),
            (source, target) -> checkDirection(source, target, new Square(1, 2))),
    DOUBLE_LEFT_UP((x, y) -> new Square(x - 2, y - 1),
            (source, target) -> checkDirection(source, target, new Square(-2, -1))),
    DOUBLE_LEFT_DOWN((x, y) -> new Square(x - 2, y + 1),
            (source, target) -> checkDirection(source, target, new Square(-2, 1))),
    DOUBLE_RIGHT_UP((x, y) -> new Square(x + 2, y - 1),
            (source, target) -> checkDirection(source, target, new Square(2, -1))),
    DOUBLE_RIGHT_DOWN((x, y) -> new Square(x + 2, y + 1),
            (source, target) -> checkDirection(source, target, new Square(2, 1)));

    BiFunction<Integer, Integer, Square> strategy;
    BiFunction<Square, Square, Boolean> checkStrategy;

    Direction(BiFunction<Integer, Integer, Square> strategy, BiFunction<Square, Square, Boolean> checkStrategy) {
        this.checkStrategy = checkStrategy;
        this.strategy = strategy;
    }

    Square move(int x, int y) {
        return strategy.apply(x, y);
    }

    boolean check(Square source, Square target) {
        return checkStrategy.apply(source, target);
    }

   public static Direction valuesOf(Square source, Square target) {
        return Arrays.stream(values())
                .filter(e -> e.check(source, target))
                .findAny()
                .orElse(null);
    }

    private static boolean checkDirection(Square source, Square target, Square pivDirection) {
        int compareX = target.getX() - source.getX();
        int compareY = target.getY() - source.getY();

        double expectScala = ((double) compareX) / pivDirection.getX();
        if(expectScala < 0) return false;

        return expectScala * pivDirection.getY() == compareY;
    }
}
