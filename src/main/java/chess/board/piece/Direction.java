package chess.board.piece;

import java.util.Arrays;

public enum Direction {
    LEFT_UP(-1, 1),
    UP(0, 1),
    RIGHT_UP(1, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_DOWN(-1, -1),
    DOWN(0, -1),
    RIGHT_DOWN(1, -1);

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction findByValue(int file, int rank) {
        return Arrays.stream(values())
                .filter(aFile -> aFile.file == file)
                .filter(aRank -> aRank.rank == rank)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
