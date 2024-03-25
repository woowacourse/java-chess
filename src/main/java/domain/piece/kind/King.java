package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;

import java.util.List;

public class King extends Piece {
    public King(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KING;
    }

    public boolean canMove(final Point movePoint, final List<Piece> pieceList) {
        return canMovePoint(movePoint) && hasEnemyPieceOrEmpty(movePoint, new Pieces(pieceList));
    }

    private boolean canMovePoint(final Point movePoint) {
        final Direction direction = this.point.calculate(movePoint);
        if (direction.canMovePoint(this.point)) {
            return direction.movePoint(this.point)
                            .equals(movePoint);
        }
        return false;
    }
}
