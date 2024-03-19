package domain.piece;

import domain.piece.point.Point;

public class Rook extends Piece {
    public Rook(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.ROOK;
    }
}
