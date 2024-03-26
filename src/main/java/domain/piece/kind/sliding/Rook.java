package domain.piece.kind.sliding;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

import domain.piece.attribute.point.Movement;
import domain.piece.kind.PieceStatus;
import java.util.Set;

import static domain.piece.attribute.point.Movement.DOWN;
import static domain.piece.attribute.point.Movement.LEFT;
import static domain.piece.attribute.point.Movement.RIGHT;
import static domain.piece.attribute.point.Movement.UP;

public class Rook extends SlidingPiece {

    public Rook(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.ROOK;
    }

    @Override
    protected Set<Movement> getMovableDirection() {
        return Set.of(UP, DOWN, RIGHT, LEFT);
    }

    @Override
    protected Piece update(Point point) {
        return null;
    }

    public boolean canMove(final Point point) {
        return false;
    }
}
