package chess.model.piece;

import chess.model.Color;
import chess.model.Type;
import chess.model.position.Distance;

public abstract class Piece {

    private final Color color;
    private final Type type;

    public Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isMovable(final Distance distance, final Color targetColor);

    public abstract Piece update();

    public boolean isEmpty() {
        return false;
    }

    public boolean isDifferentColor(final PieceColor color) {
        return this.color.isDifferent(color);
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isSameColor(final Color targetColor) {
        return this.color == targetColor;
    }

    public boolean isKing() {
        return false;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public double getScore() {
        return type.getScore();
    }
}
