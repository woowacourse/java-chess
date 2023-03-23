package chess.model.position;

import chess.model.piece.Direction;

public class Distance {

    private final int file;
    private final int rank;

    public Distance(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean matchByDirection(final Direction direction) {
        return direction.match(file, rank);
    }

    public Direction findDirection() {
        return Direction.findDirection(rank, file);
    }

    public int rank() {
        return rank;
    }

    public int file() {
        return file;
    }
}
