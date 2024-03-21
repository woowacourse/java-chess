package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Piece {

    protected final List<Direction> directions = new ArrayList<>();
    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
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
        return Objects.equals(directions, piece.directions) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(directions, color);
    }
}
