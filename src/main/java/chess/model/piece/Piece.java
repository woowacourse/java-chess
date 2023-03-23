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

    public abstract boolean isPawn();

    public abstract boolean isEmpty();

    public abstract boolean isKing();

    public final boolean isDifferentColor(final PieceColor color) {
        return this.color.isDifferent(color);
    }

    public final boolean isSameColor(final Color targetColor) {
        return this.color == targetColor;
    }

    public final Color getColor() {
        return color;
    }

    public final Type getType() {
        return type;
    }

    public final double getScore() {
        return type.getScore();
    }
}
