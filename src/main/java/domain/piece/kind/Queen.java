package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import java.util.Set;

public class Queen extends Piece {
    public Queen(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.QUEEN;
    }

    @Override
    protected Set<Point> findLegalMovePoints(Pieces pieces) {
        return null;
    }

    @Override
    protected Piece update(Point point) {
        return null;
    }

    public boolean canMove(final Point point) {
        this.point.calculate(point);
        return true;
    }
}
