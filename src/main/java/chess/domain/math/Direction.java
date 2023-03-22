package chess.domain.math;

import chess.domain.Position;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Direction {
    UP(Direction::isUp),
    DOWN(Direction::isDown),
    LEFT(Direction::isLeft),
    RIGHT(Direction::isRight),
    UP_LEFT(Direction::isUpLeft),
    UP_RIGHT(Direction::isUpRight),
    DOWN_LEFT(Direction::isDownLeft),
    DOWN_RIGHT(Direction::isDownRight),
    KNIGHT(Direction::isKnight);

    private final BiPredicate<Position, Position> direction;

    Direction(BiPredicate<Position, Position> direction) {
        this.direction = direction;
    }

    public static Direction findDirection(final Position current, final Position target) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.direction.test(current, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 갈 수 없는 방향입니다."));
    }

    private static boolean isUp(final Position current, final Position target) {
        int rowDifferent = current.getRow() - target.getRow();
        int colDifferent = current.getColumn() - target.getColumn();
        return rowDifferent > 0 && colDifferent == 0;
    }

    private static boolean isDown(final Position current, final Position target) {
        int rowDifferent = current.getRow() - target.getRow();
        int colDifferent = current.getColumn() - target.getColumn();
        return rowDifferent < 0 && colDifferent == 0;
    }

    private static boolean isLeft(final Position current, final Position target) {
        int rowDifferent = current.getRow() - target.getRow();
        int colDifferent = current.getColumn() - target.getColumn();
        return rowDifferent == 0 && colDifferent > 0;
    }

    private static boolean isRight(final Position current, final Position target) {
        int rowDifferent = current.getRow() - target.getRow();
        int colDifferent = current.getColumn() - target.getColumn();
        return rowDifferent == 0 && colDifferent < 0;
    }

    private static boolean isUpLeft(final Position current, final Position target) {
        int rowDifferent = current.getRow() - target.getRow();
        int colDifferent = current.getColumn() - target.getColumn();
        return Math.abs(rowDifferent) == Math.abs(colDifferent) && colDifferent > 0 && rowDifferent > 0;
    }

    private static boolean isUpRight(final Position current, final Position target) {
        int rowDifferent = current.getRow() - target.getRow();
        int colDifferent = current.getColumn() - target.getColumn();
        return Math.abs(rowDifferent) == Math.abs(colDifferent) && colDifferent < 0 && rowDifferent > 0;
    }

    private static boolean isDownLeft(final Position current, final Position target) {
        int rowDifferent = current.getRow() - target.getRow();
        int colDifferent = current.getColumn() - target.getColumn();
        return Math.abs(rowDifferent) == Math.abs(colDifferent) && colDifferent > 0 && rowDifferent < 0;
    }

    private static boolean isDownRight(final Position current, final Position target) {
        int rowDifferent = current.getRow() - target.getRow();
        int colDifferent = current.getColumn() - target.getColumn();
        return Math.abs(rowDifferent) == Math.abs(colDifferent) && colDifferent < 0 && rowDifferent < 0;
    }

    private static boolean isKnight(final Position current, final Position target) {
        int rowDifferent = Math.abs(current.getRow() - target.getRow());
        int colDifferent = Math.abs(current.getColumn() - target.getColumn());
        return (rowDifferent == 2 && colDifferent == 1) || (rowDifferent == 1 && colDifferent == 2);
    }

    public static List<Direction> ofAllExpectKnight() {
        List<Direction> directions = Arrays.stream(values())
                .collect(Collectors.toList());
        directions.remove(Direction.KNIGHT);

        return directions;
    }

    public static List<Direction> ofCross() {
        return List.of(UP, RIGHT, DOWN, LEFT);
    }

    public static List<Direction> ofDiagonal() {
        return List.of(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> ofKnight() {
        return List.of(KNIGHT);
    }

    public static List<Direction> ofWhitePawn() {
        return List.of(UP, UP_RIGHT, UP_LEFT);
    }

    public static List<Direction> ofBlackPawn() {
        return List.of(DOWN, DOWN_RIGHT, DOWN_LEFT);
    }
}
