package chess.domain.direction;

import chess.domain.position.Position;
import java.util.ArrayList;
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

    NONE(0, 0);

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction findAllDirection(Position source, Position target) {
        Direction crossDirection = findCrossDirection(source, target);

        if (crossDirection == NONE) {
            return findDiagonalDirection(source, target);
        }

        return crossDirection;
    }

    public static Direction findCrossDirection(Position source, Position target) {
        if (source.isHorizontal(target) && source.isLeftSide(target)) {
            return LEFT;
        }
        if (source.isHorizontal(target) && source.isRightSide(target)) {
            return RIGHT;
        }
        if (source.isVertical(target) && source.isDownSide(target)) {
            return DOWN;
        }
        if (source.isVertical(target) && source.isUpSide(target)) {
            return UP;
        }
        return NONE;
    }

    public static Direction findDiagonalDirection(Position source, Position target) {
        if (!source.isDiagonal(target)) {
            return NONE;
        }
        if (source.isRightSide(target) && source.isUpSide(target)) {
            return RIGHT_UP_DIAGONAL;
        }
        if (source.isLeftSide(target) && source.isUpSide(target)) {
            return LEFT_UP_DIAGONAL;
        }
        if (source.isRightSide(target) && source.isDownSide(target)) {
            return RIGHT_DOWN_DIAGONAL;
        }
        if (source.isLeftSide(target) && source.isDownSide(target)) {
            return LEFT_DOWN_DIAGONAL;
        }
        return NONE;
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

}
