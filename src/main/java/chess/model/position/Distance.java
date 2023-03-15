package chess.model.position;

import chess.model.piece.Direction;

public class Distance implements IndexConvertable {

    private final int rank;
    private final int file;

    public Distance(final int rank, final int file) {
        this.rank = rank;
        this.file = file;
    }

    public Direction findDirection() {
        return Direction.findDirection(rank, file);
    }

    @Override
    public int convertToIndex() {
        return Math.abs((file * FILE_MAX_SIZE) + rank);
    }

    public int rank() {
        return rank;
    }

    public int file() {
        return file;
    }
}
