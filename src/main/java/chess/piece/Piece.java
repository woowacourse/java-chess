package chess.piece;

import chess.Position;

public abstract class Piece {

    protected final Color color;
    protected final Position position;

    public Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
