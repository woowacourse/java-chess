package chess.model.piece;

import chess.model.Color;
import chess.model.Type;
import chess.model.position.Distance;

public abstract class Piece implements InitialPiece {

    final Color color;
    final Type type;

    Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public Piece pick() {
        return this;
    }

    public abstract boolean movable(final Distance distance);

    public boolean isSameTeam(final Color color) {
        return this.color.equals(color);
    }

    public boolean isPawn() {
        return type.isPawn();
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
