package domain.piece;

import domain.piece.point.Point;

public class Bishop extends Piece {
    public Bishop(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.BISHOP;
    }
}
