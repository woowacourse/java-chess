package chess.domain.piece;

import chess.domain.position.Movement;
import chess.domain.position.Position;
import java.util.List;

public abstract class SlidingPiece extends Piece {

    public SlidingPiece(final Color color) {
        super(color);
    }

    @Override
    public final boolean isEmpty() {
        return false;
    }

    @Override
    public final Movement searchMovement(final Position from, final Position to, final Piece destination) {
        final Movement movement = to.convertMovement(from);
        validateMovable(movement, getCanMoveDestination());
        return movement;
    }

    protected abstract List<Movement> getCanMoveDestination();
}
