package chess.domain.square;

import java.util.Objects;

public class King extends Piece {

    private static final String BLACK_DISPLAY = "♔";
    private static final String WHITE_DISPLAY = "♚";

    public King(Color color) {
        super(color);
    }

    @Override
    public String display() {
        if (color == Color.BLACK) {
            return BLACK_DISPLAY;
        }
        return WHITE_DISPLAY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        King king = (King) o;
        return color == king.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return color + " KING";
    }
}

