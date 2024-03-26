package domain.piece.kind.jumping;

import static domain.piece.attribute.point.Movement.DOWN;
import static domain.piece.attribute.point.Movement.LEFT;
import static domain.piece.attribute.point.Movement.LEFT_DOWN;
import static domain.piece.attribute.point.Movement.LEFT_UP;
import static domain.piece.attribute.point.Movement.RIGHT;
import static domain.piece.attribute.point.Movement.RIGHT_DOWN;
import static domain.piece.attribute.point.Movement.RIGHT_UP;
import static domain.piece.attribute.point.Movement.UP;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Movement;
import domain.piece.kind.PieceStatus;
import java.util.Set;

public class King extends JumpingPiece{
    public King(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KING;
    }

    @Override
    protected Set<Movement> getMovableDirection() {
        return Set.of(UP, DOWN, LEFT, RIGHT, RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);
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
