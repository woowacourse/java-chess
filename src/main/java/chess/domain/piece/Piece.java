package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

abstract public class Piece {

    private char name;
    protected Color color;

    protected Piece(final Color color, final char name) {
        this.name = name;
        this.color = color;
    }

    public boolean isEnemy(final Piece piece) {
        return piece.color != color;
    }

    // TODO: override 할까? (Pawn만 true 반환)
    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public abstract Direction findDirection(final Square current, final Square destination);

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
    }

    public char getName() {
        return name;
    }
}
