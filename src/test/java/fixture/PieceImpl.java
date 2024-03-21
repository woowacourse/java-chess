package fixture;

import domain.piece.attribute.Color;
import domain.piece.Piece;
import domain.piece.kind.PieceStatus;
import domain.piece.attribute.point.Point;

public class PieceImpl extends Piece {
    public PieceImpl(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        throw new UnsupportedOperationException("사용할 수 없습니다.");
    }

    @Override
    public boolean canMove(final Point point) {
        throw new UnsupportedOperationException("사용할 수 없습니다.");
    }
}
