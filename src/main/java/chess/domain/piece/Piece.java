package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public abstract class Piece {

    protected final Color color;
    protected final Position position;

    protected Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
}
