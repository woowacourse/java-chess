package chess.domain.piece;

import chess.domain.Color;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean movable(List<Integer> distances, Piece target);

    protected boolean isOpponent(Piece another) {
        return this.color != another.color;
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
