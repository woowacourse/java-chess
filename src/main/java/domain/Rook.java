package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

public class Rook extends Piece {
    public Rook(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.ROOK;
    }
}
