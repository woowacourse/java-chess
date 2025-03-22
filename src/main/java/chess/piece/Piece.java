package chess.piece;

import chess.Color;
import chess.Position;
import java.util.Objects;

public abstract class Piece {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    abstract public boolean canMove(Position start, Position end);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(color);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
    }
}
