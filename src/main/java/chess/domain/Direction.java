package chess.domain;

import chess.domain.postion.Position;

import java.util.List;

public enum Direction {
    TOP(0, 1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    BOTTOM(0, -1),

    TOP_RIGHT(1, 1),
    TOP_LEFT(-1, 1),
    BOTTOM_RIGHT(1, -1),
    BOTTOM_LEFT(-1, -1),

    TTR(1, 2),
    TTL(-1, 2),
    BBR(1, -2),
    BBL(-1, -2),
    RRT(2, 1),
    RRB(2, -1),
    LLT(-2, 1),
    LLB(-2, -1);

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static List<Direction> getRookDirection() {
        return List.of(TOP, RIGHT, LEFT, BOTTOM);
    }

    public static List<Direction> getBishopDirection() {
        return List.of(TOP_RIGHT, TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT);
    }

    public static List<Direction> getAllDirection() {
        List<Direction> directions = getRookDirection();
        directions.addAll(getBishopDirection());
        return directions;
    }

    public static List<Direction> getKnightDirection() {
        return List.of(TTR, TTL, BBR, BBL, RRT, RRB, LLT, LLB);
    }

    public static List<Direction> getWhitePawnDirection() {
        return List.of(TOP, TOP_RIGHT, TOP_LEFT);
    }

    public static List<Direction> getBlackPawnDirection() {
        return List.of(BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT);
    }

    public static Direction beMoveDirection(List<Direction> directions, Position source, Position target) {
        return directions.stream()
                .filter(direction -> Direction.isRightDirection(direction, source, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 방향입니다."));
    }

    public static boolean isRightDirection(Direction direction, Position source, Position target) {
        Position currentPosition = source;
        for (int i = 0; i < 8; i++) {
            currentPosition = currentPosition.from(direction);

            if (currentPosition.isNothing()) {
                break;
            }

            if (currentPosition.equals(target)) {
                return true;
            }
        }

        return false;
    }

    public int file() {
        return file;
    }

    public int rank() {
        return rank;
    }
}
