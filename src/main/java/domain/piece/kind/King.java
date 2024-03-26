package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import java.util.Set;

public class King extends Piece {
    public King(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KING;
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
        final var direction = this.point.calculate(point);
        final var index = this.point.toIndex();

        return Point.fromIndex(index.move(direction))
                    .equals(point);
    }
}
