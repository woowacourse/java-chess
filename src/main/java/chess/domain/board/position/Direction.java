package chess.domain.board.position;

import chess.domain.piece.attribute.Color;
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
    TTR(1, 2),
    RRT(2, 1),
    RRD(2, -1),
    DDR(1, -2),
    DDL(-1, -2),
    LLD(-2, -1),
    LLT(-2, 1),
    TTL(-1, 2);

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
                .orElseThrow(() -> new IllegalArgumentException("해당하는 방향이 없습니다."));
    }

    public static List<Direction> royalDirection(Color color) {
        return getColorDirections(color, List.of(TOP, LEFT, DOWN, RIGHT,
                TOP_LEFT, TOP_RIGHT, DOWN_LEFT, DOWN_RIGHT));
    }

    public static List<Direction> getColorDirections(Color color, List<Direction> directions) {
        if (color == Color.WHITE) {
            return directions;
        }
        return directions.stream()
                .map(Direction::toReversed)
                .collect(Collectors.toList());
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
                .orElseThrow(() -> new IllegalArgumentException("해당 방향의 반대 방향이 없습니다."));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
