package chess.domain.piece;

import java.util.Arrays;

public enum Direction {

    UPPER(0, 1),
    LOWER(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    TWO_UPPER(0, 2),
    TWO_LOWER(0, -2),

    UPPER_LEFT(-1, 1),
    UPPER_RIGHT(1, 1),
    LOWER_LEFT(-1, -1),
    LOWER_RIGHT(1, -1),

    KNIGHT_UPPER_LEFT(-1, 2),
    KNIGHT_UPPER_RIGHT(1, 2),
    KNIGHT_LOWER_LEFT(-1, -2),
    KNIGHT_LOWER_RIGHT(1, -2),
    KNIGHT_LEFT_UPPER(-2, 1),
    KNIGHT_LEFT_LOWER(-2, -1),
    KNIGHT_RIGHT_UPPER(2, 1),
    KNIGHT_RIGHT_LOWER(2, -1);

    private final int directionOfFile;
    private final int directionOfRank;

    Direction(int directionOfFile, int directionOfRank) {
        this.directionOfFile = directionOfFile;
        this.directionOfRank = directionOfRank;
    }

    public static Direction of(int file, int rank) {
        return Arrays.stream(values())
                .filter(direction -> direction.isMatch(file, rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    private boolean isMatch(int file, int rank) {
        return this.directionOfFile == file && this.directionOfRank == rank;
    }

    public int rank() {
        return directionOfRank;
    }

    public int file() {
        return directionOfFile;
    }
}
