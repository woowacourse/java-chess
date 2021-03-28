package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum Direction {
    UP((source, target) ->
            source.sameX(target.getX()) && !source.largeY(target), 0, 1),
    DOWN((source, target) ->
            source.sameX(target.getX()) && source.largeY(target), 0, -1),
    RIGHT((source, target) ->
            !source.largeX(target) && source.sameY(target.getY()), 1, 0),
    LEFT((source, target) ->
            source.largeX(target) && source.sameY(target.getY()), -1, 0),
    UP_RIGHT((source, target) ->
            source.isDiagonal(target) && !source.largeX(target) && !source.largeY(target),
            1, 1),
    UP_LEFT((source, target) ->
            source.isDiagonal(target) && source.largeX(target) && !source.largeY(target),
            -1, 1),
    DOWN_RIGHT((source, target) ->
            source.isDiagonal(target) && !source.largeX(target) && source.largeY(target),
            1, -1),
    DOWN_LEFT((source, target) ->
            source.isDiagonal(target) && source.largeX(target) && source.largeY(target),
            -1, -1),
    UP_UP_RIGHT((source, target) ->
            (source.xDistance(target) == -1) && (source.yDistance(target) == -2),
            1, 2),
    UP_UP_LEFT((source, target) ->
            (source.xDistance(target) == 1) && (source.yDistance(target) == -2),
            -1, 2),
    DOWN_DOWN_LEFT((source, target) ->
            (source.xDistance(target) == 1) && (source.yDistance(target) == 2),
            -1, -2),
    DOWN_DOWN_RIGHT((source, target) ->
            (source.xDistance(target) == -1) && (source.yDistance(target) == 2),
            1, -2),
    RIGHT_RIGHT_UP((source, target) ->
            (source.xDistance(target) == -2) && (source.yDistance(target) == -1),
            2, 1),
    RIGHT_RIGHT_DOWN((source, target) ->
            (source.xDistance(target) == -2) && (source.yDistance(target) == 1),
            2, -1),
    LEFT_LEFT_UP((source, target) ->
            (source.xDistance(target) == 2) && (source.yDistance(target) == -1),
            -2, 1),
    LEFT_LEFT_DOWN((source, target) ->
            (source.xDistance(target) == 2) && (source.yDistance(target) == 1),
            -2, -1);

    private final BiPredicate<Position, Position> findDirection;
    private final int xChange;
    private final int yChange;

    Direction(BiPredicate<Position, Position> findDirection, int xChange, int yChange) {
        this.findDirection = findDirection;
        this.xChange = xChange;
        this.yChange = yChange;
    }

    public static Direction findDirectionByTwoPosition(Position source, Position target) {
        return Arrays.stream(values())
                .filter(value -> value.findDirection.test(source, target))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 일치하는 방향이 없습니다."));
    }

    public static List<Direction> crossDirection() {
        return Arrays.asList(UP, RIGHT, DOWN, LEFT);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(UP_LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(DOWN, DOWN_LEFT, DOWN_RIGHT);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(UP, UP_LEFT, UP_RIGHT);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(UP_UP_LEFT, UP_UP_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT,
                RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN, LEFT_LEFT_UP, LEFT_LEFT_DOWN);
    }

    public int getXChange() {
        return xChange;
    }

    public int getYChange() {
        return yChange;
    }
}
