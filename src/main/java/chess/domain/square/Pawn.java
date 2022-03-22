package chess.domain.square;

import java.util.Objects;

public class Pawn implements Square {

    private static final String BLACK_PAWN_DISPLAY = "♗";
    private static final String WHITE_PAWN_DISPLAY = "♝";

    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public String display() {
        if (color == Color.BLACK) {
            return BLACK_PAWN_DISPLAY;
        }
        return WHITE_PAWN_DISPLAY;
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
        return color + " PAWN";
    }
}
