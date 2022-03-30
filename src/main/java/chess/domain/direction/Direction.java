package chess.domain.direction;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    UP(0, 1, 0),
    DOWN(0, -1, 180),
    LEFT(-1, 0, -90),
    RIGHT(1, 0, 90),

    LEFT_UP_DIAGONAL(-1, 1, -45.0),
    LEFT_DOWN_DIAGONAL(-1, -1, -135.0),
    RIGHT_UP_DIAGONAL(1, 1, 45.0),
    RIGHT_DOWN_DIAGONAL(1, -1, 135.0),

    LEFT_LEFT_UP_DIAGONAL(-2, 1, -63.0),
    LEFT_LEFT_DOWN_DIAGONAL(-2, -1, -117.0),
    LEFT_UP_UP_DIAGONAL(-1, 2, -27.0),
    LEFT_DOWN_DOWN_DIAGONAL(-1, -2, -153),
    RIGHT_RIGHT_UP_DIAGONAL(2, 1, 63.0),
    RIGHT_RIGHT_DOWN_DIAGONAL(2, -1, 117.0),
    RIGHT_UP_UP_DIAGONAL(1, 2, 27.0),
    RIGHT_DOWN_DOWN_DIAGONAL(1, -2, 153.0),

    NONE(0, 0, -99999999.9);

    private final int file;
    private final int rank;
    private final double degree;

    Direction(int file, int rank, double degree) {
        this.file = file;
        this.rank = rank;
        this.degree = degree;
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
        double degree = findDegree(source, target);

        return findDirectionByDegree(degree);
    }

    public static Direction findDirectionByDegree(double degree) {
        return Arrays.stream(values())
            .filter(direction -> direction.degree == degree)
            .findFirst()
            .orElse(NONE);
    }

    public static double findDegree(Position source, Position target) {
        return source.findRelativeDegree(target);
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
        return this == LEFT_DOWN_DIAGONAL
            || this == RIGHT_DOWN_DIAGONAL
            || this == LEFT_UP_DIAGONAL
            || this == RIGHT_UP_DIAGONAL;
    }

    public boolean isCrossDirection() {
        return this == LEFT
            || this == RIGHT
            || this == UP
            || this == DOWN;
    }

}
