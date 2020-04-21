package chess.domain.direction;

import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum Direction {
    NORTH_NORTH_EAST((rowDiff, columnDiff) -> rowDiff == 1 && columnDiff == 2, new KnightPositionBetween()),
    NORTH_NORTH_WEST((rowDiff, columnDiff) -> rowDiff == -1 && columnDiff == 2, new KnightPositionBetween()),
    EAST_EAST_NORTH((rowDiff, columnDiff) -> rowDiff == 2 && columnDiff == 1, new KnightPositionBetween()),
    EAST_EAST_SOUTH((rowDiff, columnDiff) -> rowDiff == 2 && columnDiff == -1, new KnightPositionBetween()),
    SOUTH_SOUTH_EAST((rowDiff, columnDiff) -> rowDiff == 1 && columnDiff == -2, new KnightPositionBetween()),
    SOUTH_SOUTH_WEST((rowDiff, columnDiff) -> rowDiff == -1 && columnDiff == -2, new KnightPositionBetween()),
    WEST_WEST_SOUTH((rowDiff, columnDiff) -> rowDiff == -2 && columnDiff == -1, new KnightPositionBetween()),
    WEST_WEST_NORTH((rowDiff, columnDiff) -> rowDiff == -2 && columnDiff == 1, new KnightPositionBetween()),
    WEST((rowDiff, columnDiff) -> isPositive(rowDiff) && isZero(columnDiff),
            new EastWestPositionBetween()),
    EAST((rowDiff, columnDiff) -> isNegative(rowDiff) && isZero(columnDiff),
            new EastWestPositionBetween()),
    NORTH((rowDiff, columnDiff) -> isZero(rowDiff) && isNegative(columnDiff),
            new NorthSouthPositionBetween()),
    SOUTH((rowDiff, columnDiff) -> isZero(rowDiff) && isPositive(columnDiff),
            new NorthSouthPositionBetween()),
    NORTH_WEST((rowDiff, columnDiff) -> isPositive(rowDiff) && isNegative(columnDiff),
            new DiagonalPositionBetween()),
    NORTH_EAST((rowDiff, columnDiff) -> isNegative(rowDiff) && isNegative(columnDiff),
            new DiagonalPositionBetween()),
    SOUTH_WEST((rowDiff, columnDiff) -> isPositive(rowDiff) && isPositive(columnDiff),
            new DiagonalPositionBetween()),
    SOUTH_EAST((rowDiff, columnDiff) -> isNegative(rowDiff) && isPositive(columnDiff),
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
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        int rowDiff = Row.getDiff(from.getRow(), to.getRow());
        int columnDiff = Column.getDiff(from.getColumn(), to.getColumn());
        System.out.println("row diff: "+rowDiff);
        System.out.println("column diff: "+columnDiff);
        return judge.test(rowDiff, columnDiff);
    }

    public List<Position> getPositionsBetween(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        return positionsBetween.apply(from, to);
    }

    public static Direction getDirection(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        return Arrays.stream(Direction.values())
                .filter(x -> x.getJudge(from, to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이동입니다."));
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NORTH_NORTH_EAST, NORTH_NORTH_WEST, EAST_EAST_NORTH,
                EAST_EAST_SOUTH, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
                WEST_WEST_SOUTH, WEST_WEST_NORTH);
    }
}
