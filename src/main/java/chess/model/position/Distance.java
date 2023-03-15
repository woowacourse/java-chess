package chess.model.position;

import chess.model.piece.Direction;

public class Distance implements IndexConvertable {

    private final int file;
    private final int rank;

    public Distance(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public Direction findDirection() {
        return Direction.findDirection(rank, file);
    }

    @Override
    public int convertToIndex() {
        return Math.abs((rank * FILE_MAX_SIZE) + file);
    }

    public int rank() {
        return rank;
    }

    public int file() {
        return file;
    }
}
