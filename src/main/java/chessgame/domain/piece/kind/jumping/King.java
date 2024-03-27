package chessgame.domain.piece.kind.jumping;

import chessgame.domain.piece.attribute.point.Movement;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.kind.PieceStatus;
import java.util.Set;

public class King extends JumpingPiece{
    public King(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus status() {
        return PieceStatus.KING;
    }

    @Override
    protected Set<Movement> getMovableDirection() {
        return Set.of(Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT, Movement.RIGHT_UP, Movement.RIGHT_DOWN, Movement.LEFT_UP, Movement.LEFT_DOWN);
    }

    @Override
    protected Piece update(Point point) {
        return new King(point, color);
    }

}
