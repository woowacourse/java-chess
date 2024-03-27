package chessgame.domain.piece.kind.sliding;

import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.attribute.point.Movement;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.kind.PieceStatus;
import chessgame.domain.piece.Piece;

import java.util.Set;

public class Rook extends SlidingPiece {

    public Rook(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus status() {
        return PieceStatus.ROOK;
    }

    @Override
    protected Set<Movement> getMovableDirection() {
        return Set.of(Movement.UP, Movement.DOWN, Movement.RIGHT, Movement.LEFT);
    }

    @Override
    protected Piece update(final Point point) {
        return new Rook(point, color);
    }

}
