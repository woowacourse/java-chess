package chess2.domain.square;

import java.util.Objects;

public class Pawn implements Square {

    private final Color color;

    public Pawn(Color color) {
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
        return color == pawn.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "Pawn{" + "color=" + color + '}';
    }
}
