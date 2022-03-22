package chess.domain.square;

import java.util.Objects;

public class Knight extends Piece {

    private static final String BLACK_DISPLAY = "♘";
    private static final String WHITE_DISPLAY = "♞";

    public Knight(Color color) {
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
        Knight knight = (Knight) o;
        return color == knight.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return color + " KNIGHT";
    }
}
