package domain.piece.kind.sliding;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

import domain.piece.attribute.point.TempDirection;
import domain.piece.kind.PieceStatus;
import java.util.Set;

import static domain.piece.attribute.point.TempDirection.LEFT_DOWN;
import static domain.piece.attribute.point.TempDirection.LEFT_UP;
import static domain.piece.attribute.point.TempDirection.RIGHT_DOWN;
import static domain.piece.attribute.point.TempDirection.RIGHT_UP;


public class Bishop extends SlidingPiece {
    public Bishop(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    protected Set<TempDirection> getMovableDirection() {
        return Set.of(LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN);
    }

    @Override
    protected Piece update(Point point) {
        return null;
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.BISHOP;
    }

    public boolean canMove(final Point point) {
        return false;
    }
}
