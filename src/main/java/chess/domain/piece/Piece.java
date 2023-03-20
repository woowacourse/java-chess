package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

abstract public class Piece {

    protected Color color;
    private String name;

    protected Piece(final Color color, final String name) {
        this.name = name;
        this.color = color;
    }

    public boolean isEnemy(final Piece piece) {
        return piece.color != color;
    }

    public boolean isAlly(final Color color) {
        return this.color == color;
    }

    // TODO: override 할까? (Pawn만 true 반환)
    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public abstract Direction findDirection(final Square current, final Square destination);

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
