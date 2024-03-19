package fixture;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceStatus;
import domain.piece.point.Point;

public class PieceImpl extends Piece {
    public PieceImpl(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        throw new UnsupportedOperationException("사용할 수 없습니다.");
    }
}
