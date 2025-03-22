package chess.piece;

import chess.Color;
import chess.Position;

public abstract class AbstractPiece implements Piece {
    private final String name;
    private final Color color;
    private final Position position;

    public AbstractPiece(final String name, final Color color, final Position position) {
        this.name = name;
        this.color = color;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
}
