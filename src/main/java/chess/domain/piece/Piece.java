package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public abstract class Piece {
    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position from, Position to, Piece piece);

    public abstract boolean canJump();

    public Color getColor() {
        return color;
    }
}
