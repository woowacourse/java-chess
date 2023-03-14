package techcourse.fp.chess.domain;

public abstract class Piece {

    private final Side side;

    protected Piece(final Side side) {
        this.side = side;
    }

    public abstract boolean isMovable(final Position sourcePosition, final Position targetPosition);
}
