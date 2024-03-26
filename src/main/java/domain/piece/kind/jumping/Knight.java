package domain.piece.kind.jumping;

import static domain.piece.attribute.point.Movement.*;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

import domain.piece.attribute.point.Movement;
import domain.piece.kind.PieceStatus;
import java.util.Set;

public class Knight extends JumpingPiece {
    public Knight(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    protected Set<Movement> getMovableDirection() {
        return Set.of(LEFT_LEFT_UP, LEFT_UP_UP, LEFT_LEFT_DOWN, LEFT_DOWN_DOWN, RIGHT_RIGHT_UP, RIGHT_UP_UP,
                RIGHT_RIGHT_DOWN, RIGHT_DOWN_DOWN);
    }

    public boolean canMove(final Point point) {
        return false;
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KNIGHT;
    }

    @Override
    protected Piece update(Point point) {
        return null;
    }
}
