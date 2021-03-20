package chess.domain.board;

import chess.domain.piece.TeamType;
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

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction findDirection(int fileDiff, int rankDiff) {
        if (fileDiff == 0 && rankDiff == 0) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (fileDiff == 0) {
            return verticalDirection(rankDiff);
        }
        if (rankDiff == 0) {
            return horizontalDirection(fileDiff);
        }
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return diagonalDirection(fileDiff, rankDiff);
        }
        if (Math.abs(fileDiff) + Math.abs(rankDiff) == 3) {
            return knightDirection(fileDiff, rankDiff);
        }
        throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
    }

    private static Direction knightDirection(int fileDiff, int rankDiff) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.x == fileDiff && direction.y == rankDiff)
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
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

    private static Direction verticalDirection(int rankDiff) {
        if (rankDiff > 0) {
            return UP;
        }
        return DOWN;
    }

    private static Direction horizontalDirection(int fileDiff) {
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

    public boolean isDiagonal() {
        List<Direction> diagonalDirections
            = Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
        return diagonalDirections.contains(this);
    }

    public static List<Direction> getKingDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }


    public static List<Direction> getBishopDirections() {
        return Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }

    public static List<Direction> getQueenDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }

    public static List<Direction> getKnightDirections() {
        return Arrays.asList(Direction.LEFT_LEFT_DOWN, Direction.LEFT_LEFT_UP, Direction.LEFT_UP_UP,
            Direction.LEFT_DOWN_DOWN,
            Direction.RIGHT_DOWN_DOWN, Direction.RIGHT_UP_UP, Direction.RIGHT_RIGHT_UP,
            Direction.RIGHT_RIGHT_DOWN);
    }

    public static List<Direction> getRookDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN);
    }

    public static List<Direction> getPawnDirections(TeamType teamType) {
        if (teamType == TeamType.BLACK) {
            return Arrays.asList(DOWN, LEFT_DOWN, RIGHT_DOWN);
        }
        return Arrays.asList(UP, LEFT_UP, RIGHT_UP);
    }
}
