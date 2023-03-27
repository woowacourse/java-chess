package domain.piece;

import domain.piece.move.Coordinate;

public final class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Color.NEUTRAL);
    }

    public EmptyPiece(final Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(
            final Coordinate start,
            final Coordinate end
    ) {
        return false;
    }

    @Override
    public boolean isAttackable(
            final Coordinate start,
            final Coordinate end,
            final Piece target
    ) {
        return false;
    }
}
