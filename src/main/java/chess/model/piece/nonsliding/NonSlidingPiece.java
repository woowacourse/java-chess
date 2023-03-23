package chess.model.piece.nonsliding;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.position.Direction;
import chess.model.position.Distance;

public abstract class NonSlidingPiece extends Piece {

    public NonSlidingPiece(final Color color, final Type type) {
        super(color, type);
    }

    protected abstract boolean isRightDirection(final Direction direction);

    protected abstract boolean isRightDistance(final Distance distance);

    public final boolean isMovable(final Distance distance, final Color targetColor) {
        return isRightDirection(distance.findDirection()) && isRightDistance(distance);
    }

    @Override
    public final Piece update() {
        return this;
    }

    @Override
    public final boolean isPawn() {
        return false;
    }

    @Override
    public final boolean isEmpty() {
        return false;
    }
}
