package chess.domain.board;

import chess.domain.piece.Color;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Direction {
    TOP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    TOPLEFT(-1, 1),
    TOPRIGHT(1, 1),
    DOWNLEFT(-1, -1),
    DOWNRIGHT(1, -1),
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

    public static List<Direction> pawnDirection(Color color) {
        return getColorDirections(color, List.of(TOP, TOPLEFT, TOPRIGHT));
    }

    public static List<Direction> rookDirection(Color color) {
        return getColorDirections(color, List.of(TOP, DOWN, LEFT, RIGHT));
    }

    public static List<Direction> bishopDirection(Color color) {
        return getColorDirections(color, List.of(TOPLEFT, TOPRIGHT, DOWNLEFT, DOWNRIGHT));
    }

    public static List<Direction> kingDirection(Color color) {
        return getColorDirections(color, List.of(TOP, LEFT, DOWN, RIGHT,
                TOPLEFT, TOPRIGHT, DOWNLEFT, DOWNRIGHT));
    }

    private static List<Direction> getColorDirections(Color color, List<Direction> directions) {
        if (color == Color.WHITE) {
            return directions;
        }
        return directions.stream()
                .map(direction -> direction.toReversed())
                .collect(Collectors.toList());
    }

    public boolean isSameDirection(Position from, Position to) {
        if (y == 0) {
            return from.getYDistance(to) == 0 && from.getXDistance(to) * x > 0;
        }
        return (double) from.getXDistance(to) / from.getYDistance(to) == (double) x / y;
    }

    public boolean isSameDistance(Position from, Position to) {
        return from.getXDistance(to) == x && from.getYDistance(to) == y;
    }

    private Direction toReversed() {
        return Arrays.stream(Direction.values())
                .filter(direction ->
                        direction.getX() == (-1) * this.getX() &&
                                direction.getY() == (-1) * this.getY()
                ).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 방향의 반대 방향이 없습니다."));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
