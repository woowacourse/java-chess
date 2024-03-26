package chess.domain.piece;

import java.util.Arrays;
import java.util.Set;

public enum Direction {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_LEFT_DOWN(-2, -1),
    KNIGHT_RIGHT_UP(2, 1),
    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_DOWN_RIGHT(1, -2),
    ;

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction find(final int fileDifference, final int rankDifference) {
        return Arrays.stream(values())
                .filter(direction -> direction.isSameGradiant(fileDifference, rankDifference)
                        && direction.isSameSign(fileDifference, rankDifference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 이동 방향입니다."));
    }

    private boolean isSameGradiant(int fileDifference, int rankDifference) {
        return (double) this.file / this.rank == (double) fileDifference / rankDifference;
    }

    private boolean isSameSign(int fileDifference, int rankDifference) {
        return this.file * fileDifference >= 0 && this.rank * rankDifference >= 0;
    }

    public int calculateNextFile(final int currentFile) {
        return currentFile + this.file;
    }

    public int calculateNextRank(final int currentRank) {
        return currentRank + this.rank;
    }

    public boolean isDiagonal() {
        Set<Direction> diagonalDirections = Set.of(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
        return diagonalDirections.contains(this);
    }
}
