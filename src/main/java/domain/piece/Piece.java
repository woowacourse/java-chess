package domain.piece;

import domain.board.Position;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position source, Position target);

    public abstract String asString();

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }

    public boolean hasNotColorOf(Color color) {
        return this.color != color;
    }
}
