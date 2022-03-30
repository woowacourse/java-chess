package chess.domain.board.position;

import chess.domain.piece.attribute.Team;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Direction {
    TOP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    TOP_LEFT(-1, 1),
    TOP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1),
    TOP_TOP_RIGHT(1, 2),
    RIGHT_RIGHT_TOP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_DOWN_LEFT(-1, -2),
    LEFT_LEFT_DOWN(-2, -1),
    LEFT_LEFT_TOP(-2, 1),
    TOP_TOP_LEFT(-1, 2);

    private static final String NO_DIRECTION_ERROR_MESSAGE = "해당하는 방향이 없습니다.";

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction of(Position from, Position to) {
        return Arrays.stream(Direction.values())
                .filter(value -> value.isSameDirection(from, to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_DIRECTION_ERROR_MESSAGE));
    }

    public static List<Direction> royalDirection(Team team) {
        return getAbsoluteDirections(team, List.of(TOP, LEFT, DOWN, RIGHT,
                TOP_LEFT, TOP_RIGHT, DOWN_LEFT, DOWN_RIGHT));
    }

    public static List<Direction> getAbsoluteDirections(Team team, List<Direction> directions) {
        if (team == Team.WHITE) {
            return directions;
        }
        return directions.stream()
                .map(Direction::toReversed)
                .collect(Collectors.toList());
    }

    public static boolean isInvalidDirection(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .noneMatch(direction -> direction.isSameDirection(from, to));
    }

    public static boolean isInvalidDistance(Position from, Position to, List<Direction> directions) {
        return directions.stream()
                .noneMatch(direction -> direction.isSameDistance(from, to));
    }

    public boolean isSameDirection(Position from, Position to) {
        return Math.atan2(from.getYDistance(to), from.getXDistance(to)) == Math.atan2(y, x);
    }

    public boolean isSameDistance(Position from, Position to) {
        return from.getXDistance(to) == x && from.getYDistance(to) == y;
    }

    private Direction toReversed() {
        return Arrays.stream(Direction.values())
                .filter(value -> value.getX() == (-1) * x)
                .filter(value -> value.getY() == (-1) * y)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
