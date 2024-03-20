package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

public class King extends Piece {
    public King(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KING;
    }
}
