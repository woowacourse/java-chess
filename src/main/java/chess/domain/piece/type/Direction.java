package chess.domain.piece.type;

import chess.domain.player.type.TeamColor;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),
    LEFT_UP_UP(-1, 2),
    LEFT_DOWN_DOWN(-1, -2),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    RIGHT_UP_UP(1, 2),
    RIGHT_DOWN_DOWN(1, -2);

    private static final String CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE = "이동할 수 없는 도착 위치 입니다.";

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction of(int fileDiff, int rankDiff) {
        if (fileDiff == 0 && rankDiff == 0) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
        if (fileDiff == 0 || rankDiff == 0) {
            return verticalOrHorizontalDirection(fileDiff, rankDiff);
        }
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return diagonalDirection(fileDiff, rankDiff);
        }
        return knightDirection(fileDiff, rankDiff);
    }

    private static Direction knightDirection(int fileDiff, int rankDiff) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.x == fileDiff && direction.y == rankDiff)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE));
    }

    private static Direction diagonalDirection(int fileDiff, int rankDiff) {
        if (fileDiff > 0 && rankDiff > 0) {
            return RIGHT_UP;
        }
        if (fileDiff > 0 && rankDiff < 0) {
            return RIGHT_DOWN;
        }
        if (fileDiff < 0 && rankDiff < 0) {
            return LEFT_DOWN;
        }
        return LEFT_UP;
    }

    private static Direction verticalOrHorizontalDirection(int fileDiff, int rankDiff) {
        if (rankDiff > 0) {
            return UP;
        }
        if (rankDiff < 0) {
            return DOWN;
        }
        if (fileDiff > 0) {
            return RIGHT;
        }
        return LEFT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isForward() {
        return !isDiagonal();
    }

    public boolean isDiagonal() {
        List<Direction> diagonalDirections = Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
        return diagonalDirections.contains(this);
    }

    public static List<Direction> pawnDirections(TeamColor teamType) {
        if (teamType == TeamColor.BLACK) {
            return Arrays.asList(DOWN, LEFT_DOWN, RIGHT_DOWN);
        }
        return Arrays.asList(UP, LEFT_UP, RIGHT_UP);
    }

    public static List<Direction> knightDirections() {
        return Arrays.asList(
            Direction.LEFT_LEFT_DOWN, Direction.LEFT_LEFT_UP, Direction.RIGHT_RIGHT_DOWN, Direction.RIGHT_RIGHT_UP,
            Direction.LEFT_DOWN_DOWN, Direction.LEFT_UP_UP, Direction.RIGHT_DOWN_DOWN, Direction.RIGHT_UP_UP);
    }


    public static List<Direction> rookDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN);
    }

    public static List<Direction> bishopDirections() {
        return Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }

    public static List<Direction> queenDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }

    public static List<Direction> kingDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }
}
