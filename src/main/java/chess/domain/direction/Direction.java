package chess.domain.direction;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),

    LEFT_UP_DIAGONAL(-1, 1),
    LEFT_DOWN_DIAGONAL(-1, -1),
    RIGHT_UP_DIAGONAL(1, 1),
    RIGHT_DOWN_DIAGONAL(1, -1),

    LEFT_LEFT_UP_DIAGONAL(-2, 1),
    LEFT_LEFT_DOWN_DIAGONAL(-2, -1),
    LEFT_UP_UP_DIAGONAL(-1, 2),
    LEFT_DOWN_DOWN_DIAGONAL(-1, -2),
    RIGHT_RIGHT_UP_DIAGONAL(2, 1),
    RIGHT_RIGHT_DOWN_DIAGONAL(2, -1),
    RIGHT_UP_UP_DIAGONAL(1, 2),
    RIGHT_DOWN_DOWN_DIAGONAL(1, -2),

    NONE(0, 0);

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public List<Position> findPositionsInPath(Position source, Position target) {
        List<Position> positions = new ArrayList<>();

        if (this == NONE) {
            return positions;
        }

        return findPositionsBetween(source, target);
    }

    private List<Position> findPositionsBetween(Position source, Position target) {
        List<Position> positions = new ArrayList<>();
        int startFile = source.getFileIdx() + file;
        int startRank = source.getRankIdx() + rank;

        while (startFile != target.getFileIdx() || startRank != target.getRankIdx()) {
            positions.add(Position.from(startFile, startRank));
            startFile += file;
            startRank += rank;
        }

        return new ArrayList<>(positions);
    }

    public static Direction findDirection(Position source, Position target) {
        int rawFileDifference = source.fileRawDifference(target);
        int rawRankDifference = source.rankRawDifference(target);

        int fileDifference = source.fileDifference(target);
        int rankDifference = source.rankDifference(target);

        return getDirectionByFileAndRankDifference(rawFileDifference, rawRankDifference, fileDifference,
            rankDifference);
    }

    private static Direction getDirectionByFileAndRankDifference(int rawFileDifference, int rawRankDifference,
        int fileDifference, int rankDifference) {
        if (fileDifference == rankDifference) {
            return Direction.of(fileDifference / rawFileDifference, rankDifference / rawRankDifference);
        }
        if (fileDifference == 0) {
            return Direction.of(rawFileDifference, rankDifference / rawRankDifference);
        }
        if (rankDifference == 0) {
            return Direction.of(fileDifference / rawFileDifference, rawRankDifference);
        }
        return Direction.of(rawFileDifference, rawRankDifference);
    }

    private static Direction of(int file, int rank) {
        return Arrays.stream(values())
            .filter(direction -> direction.file == file && direction.rank == rank)
            .findFirst()
            .orElse(NONE);
    }

    public boolean isLShapeDiagonalDirection() {
        return this == LEFT_LEFT_UP_DIAGONAL
            || this == LEFT_LEFT_DOWN_DIAGONAL
            || this == LEFT_UP_UP_DIAGONAL
            || this == LEFT_DOWN_DOWN_DIAGONAL
            || this == RIGHT_RIGHT_UP_DIAGONAL
            || this == RIGHT_RIGHT_DOWN_DIAGONAL
            || this == RIGHT_UP_UP_DIAGONAL
            || this == RIGHT_DOWN_DOWN_DIAGONAL;
    }

    public boolean isAttachedDownDirection() {
        return this == DOWN;
    }

    public boolean isAttachedUpDirection() {
        return this == UP;
    }

    public boolean isDownwardDiagonalDirection() {
        return this == LEFT_DOWN_DIAGONAL
            || this == RIGHT_DOWN_DIAGONAL;
    }

    public boolean isUpwardDiagonalDirection() {
        return this == LEFT_UP_DIAGONAL
            || this == RIGHT_UP_DIAGONAL;
    }

    public boolean isDiagonalDirection() {
        return isDownwardDiagonalDirection()
            || isUpwardDiagonalDirection();
    }

    public boolean isCrossDirection() {
        return this == LEFT
            || this == RIGHT
            || this == UP
            || this == DOWN;
    }

}
