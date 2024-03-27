package chessgame.domain.piece.kind.sliding;

import chessgame.domain.piece.attribute.point.Movement;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.kind.PieceStatus;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.attribute.Color;
import java.util.Set;

public class Queen extends SlidingPiece {
    public Queen(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus status() {
        return PieceStatus.QUEEN;
    }

    @Override
    protected Set<Movement> getMovableDirection() {
        return Set.of(Movement.UP, Movement.DOWN, Movement.RIGHT, Movement.LEFT, Movement.LEFT_UP, Movement.RIGHT_UP, Movement.LEFT_DOWN, Movement.RIGHT_DOWN);
    }

    @Override
    protected Piece update(final Point point) {
        return new Queen(point, color);
    }

}
