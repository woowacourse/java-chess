package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;

public class King extends Piece {
    public King(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KING;
    }

    public boolean canMove(final Point point) {
        final Direction direction = this.point.calculate(point);
        return direction.movePoint(this.point)
                        .equals(point);
    }
}
