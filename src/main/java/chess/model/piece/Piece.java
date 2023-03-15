package chess.model.piece;

import chess.model.Color;
import chess.model.Type;
import chess.model.position.Distance;

public abstract class Piece {

    final Color color;
    final Type type;

    public Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSameTeam(final Color color) {
        return this.color.equals(color);
    }

    abstract boolean movable(final Distance distance);
}
