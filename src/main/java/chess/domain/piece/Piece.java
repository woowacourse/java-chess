package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public boolean isSameColor(Color otherColor) {
        return color == otherColor;
    }


    public abstract boolean isMovable(Position from, Position to);

    public abstract boolean isCatchable(Position from, Position to);
}
