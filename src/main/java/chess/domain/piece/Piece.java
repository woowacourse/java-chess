package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

import java.util.Objects;

abstract public class Piece {

    protected Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public boolean isEnemy(final Piece piece) {
        return piece.color != color;
    }

    public abstract Direction findDirection(final Square current, final Square destination);

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
    }
}
