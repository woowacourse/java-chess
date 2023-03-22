package chess.model.piece.sliding;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.position.Direction;
import chess.model.position.Distance;

public abstract class SlidingPiece extends Piece {

    public SlidingPiece(final Color color, final Type type) {
        super(color, type);
    }

    public final boolean isMovable(final Distance distance, final Color targetColor) {
        return isRightDirection(distance.findDirection())
                && hasTargetEnemy(targetColor);
    }

    protected abstract boolean isRightDirection(final Direction direction);

    protected boolean hasTargetEnemy(final Color targetColor) {
        return getColor().isDifferent(targetColor);
    }

    public Piece update() {
        return this;
    }
}
