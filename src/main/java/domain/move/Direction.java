package domain.move;

import domain.position.Position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),

    UP_RIGHT(1, 2),
    UP_LEFT(-1, 2),
    RIGHT_UP(2, 1),
    RIGHT_DOWN(2, -1),
    LEFT_DOWN(-2, -1),
    LEFT_UP(-2, 1),
    DOWN_RIGHT(1, -2),
    DOWN_LEFT(-1, -2);

    private final int fileVector;
    private final int rankVector;

    Direction(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    public boolean isNorthOrSouth() {
        return this == Direction.NORTH || this == Direction.SOUTH;
    }

    public boolean isDiagonalDirection() {
        return List.of(Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.NORTH_WEST)
                .contains(this);
    }

    public static Direction findDirection(Position source, Position target) {
        ChessVector targetChessVector = target.toVector(source);
        ChessVector unitChessVector = targetChessVector.toUnitVector();
        return Arrays.stream(values())
                .filter(direction -> isSameDirection(direction, unitChessVector))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 방향입니다."));
    }

    private static boolean isSameDirection(Direction direction, ChessVector chessVector) {
        return direction.fileVector == chessVector.getFileVector() && direction.rankVector == chessVector.getRankVector();
    }

    public int getFileVector() {
        return fileVector;
    }

    public int getRankVector() {
        return rankVector;
    }
}
