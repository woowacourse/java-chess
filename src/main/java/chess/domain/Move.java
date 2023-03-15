package chess.domain;

import java.util.Arrays;

public enum Move {
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP(0, 1),
    DOWN(0, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1);

    private final int file;
    private final int rank;

    Move(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Move calculateDirection(final Square source, final Square target) {
        final int directionFile = Integer.signum(target.getFile() - source.getFile());
        final int directionRank = Integer.signum(target.getRank() - source.getRank());

        return Arrays.stream(Move.values())
                .filter(move -> move.file == directionFile && move.rank == directionRank)
                .findFirst()
                .orElseThrow();
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
