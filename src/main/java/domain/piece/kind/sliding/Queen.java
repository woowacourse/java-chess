package domain.piece.kind.sliding;

import static domain.piece.attribute.point.Movement.*;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Movement;
import domain.piece.kind.PieceStatus;
import java.util.Set;

public class Queen extends SlidingPiece {
    public Queen(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.QUEEN;
    }

    @Override
    protected Set<Movement> getMovableDirection() {
        return Set.of(UP, DOWN, RIGHT, LEFT, LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN);
    }

    @Override
    protected Piece update(Point point) {
        return new Queen(point, color);
    }

}
