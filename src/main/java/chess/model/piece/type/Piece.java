package chess.model.piece.type;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Direction;
import chess.model.position.Distance;

public abstract class Piece {

    final Color color;
    final Type type;

    Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public final boolean isMovable(final Distance distance, final Color targetColor) {
        return canMove(distance, targetColor) || isRightAttack(distance, targetColor);
    }

    private boolean canMove(final Distance distance, final Color targetColor) {
        return isRightDirection(distance.findDirection())
                && isRightDistance(distance)
                && isSatisfySpecialCondition(distance, targetColor);
    }

    abstract boolean isRightDirection(final Direction direction);

    protected boolean isRightDistance(final Distance distance) {
        return true;
    }

    protected boolean isSatisfySpecialCondition(final Distance distance, final Color targetColor) {
        return true;
    }

    protected boolean isRightAttack(final Distance distance, final Color targetColor) {
        return false;
    }

    public Piece update() {
        return this;
    }

    public boolean isDifferentColor(final Color color) {
        return !this.color.equals(color);
    }

    public boolean isEmpty() {
        return false;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
