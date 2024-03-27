package chessgame.domain.piece.kind.jumping;

import chessgame.domain.piece.Piece;
import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.attribute.point.Movement;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.kind.PieceStatus;

import java.util.Set;

public class Knight extends JumpingPiece {
    public Knight(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    protected Set<Movement> getMovableDirection() {
        return Set.of(Movement.LEFT_LEFT_UP, Movement.LEFT_UP_UP, Movement.LEFT_LEFT_DOWN, Movement.LEFT_DOWN_DOWN, Movement.RIGHT_RIGHT_UP, Movement.RIGHT_UP_UP,
                Movement.RIGHT_RIGHT_DOWN, Movement.RIGHT_DOWN_DOWN);
    }

    @Override
    public PieceStatus status() {
        return PieceStatus.KNIGHT;
    }

    @Override
    protected Piece update(Point point) {
        return new Knight(point, color);
    }
}
