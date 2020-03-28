package chess.domain.direction;

import chess.Exceptions.IllegalDirectionException;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum Direction {
    LEFT((rowDiff, columnDiff) -> isPositive(rowDiff) && isZero(columnDiff),
            new LeftRightPositionBetween()),
    RIGHT((rowDiff, columnDiff) -> isNegative(rowDiff) && isZero(columnDiff),
            new LeftRightPositionBetween()),
    TOP((rowDiff, columnDiff) -> isZero(rowDiff) && isNegative(columnDiff),
            new TopDownPositionBetween()),
    DOWN((rowDiff, columnDiff) -> isZero(rowDiff) && isPositive(columnDiff),
            new TopDownPositionBetween()),
    DIAGONAL_TOP_LEFT((rowDiff, columnDiff) -> isPositive(rowDiff) && isNegative(columnDiff),
            new DiagonalPositionBetween()),
    DIAGONAL_TOP_RIGHT((rowDiff, columnDiff) -> isNegative(rowDiff) && isNegative(columnDiff),
            new DiagonalPositionBetween()),
    DIAGONAL_DOWN_LEFT((rowDiff, columnDiff) -> isPositive(rowDiff) && isPositive(columnDiff),
            new DiagonalPositionBetween()),
    DIAGONAL_DOWN_RIGHT((rowDiff, columnDiff) -> isNegative(rowDiff) && isPositive(columnDiff),
            new DiagonalPositionBetween());

    private final BiPredicate<Integer, Integer> judge;
    private final BiFunction<Position, Position, List<Position>> positionsBetween;

    Direction(BiPredicate<Integer, Integer> judge, BiFunction<Position, Position, List<Position>> positionsBetween) {
        this.judge = judge;
        this.positionsBetween = positionsBetween;
    }

    private static boolean isPositive(int number) {
        return number > 0;
    }

    private static boolean isZero(int number) {
        return number == 0;
    }

    private static boolean isNegative(int number) {
        return number < 0;
    }

    public boolean getJudge(Position from, Position to) {
        int rowDiff = Row.getDiff(from.getRow(), to.getRow());
        int columnDiff = Column.getDiff(from.getColumn(), to.getColumn());
        return judge.test(rowDiff, columnDiff);
    }

    public List<Position> getPositionsBetween(Position from, Position to) {
        return positionsBetween.apply(from, to);
    }

    public static Direction getDirection(Position from, Position to) {
        return Arrays.stream(Direction.values())
                .filter(x -> x.getJudge(from, to))
                .findFirst()
                .orElseThrow(IllegalDirectionException::new);
    }
}
