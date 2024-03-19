package domain.piece;

import domain.piece.point.Point;

public class Pawn extends Piece {
    public Pawn(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.PAWN;
    }
}
