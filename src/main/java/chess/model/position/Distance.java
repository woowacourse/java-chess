package chess.model.position;

import chess.model.piece.Direction;

public class Distance {

    private static final int FILE_MAX_SIZE = 8;

    private final int rank;
    private final int file;

    public Distance(final int rank, final int file) {
        this.rank = rank;
        this.file = file;
    }

    public int convertToIndex() {
        return (file * FILE_MAX_SIZE) + rank;
    }

    public int rank() {
        return rank;
    }

    public int file() {
        return file;
    }
}
