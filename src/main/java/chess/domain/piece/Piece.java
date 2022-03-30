package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;
import java.util.Objects;

public abstract class Piece {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean movable(Distance distance, Piece target);

    protected boolean isOpponent(Piece another) {
        return this.color != another.color;
    }

    public boolean isInValid() {
        return false;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
