package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import java.util.Objects;

public class Pawn {

    private final Position position;
    private final Color color;

    public Pawn(Position position, Color color) {
        this.position = position;
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
        Pawn pawn = (Pawn) o;
        return position == pawn.position && color == pawn.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color);
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "position=" + position +
                ", color=" + color +
                '}';
    }
}
