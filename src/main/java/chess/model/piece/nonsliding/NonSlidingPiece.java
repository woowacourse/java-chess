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

    public boolean isMovable(final Distance distance, final Color targetColor) {
        return isRightDirection(distance.findDirection()) && isRightDistance(distance);
    }

    protected abstract boolean isRightDirection(final Direction direction);

    protected boolean isRightDistance(final Distance distance) {
        return true;
    }

    @Override
    public Piece update() {
        return this;
    }
}
