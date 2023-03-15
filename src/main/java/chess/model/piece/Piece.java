package chess.model.piece;

import chess.model.Color;
import chess.model.Type;

public class Piece {

    private final Color color;
    private final Type type;

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
}
