package chess.domain.piece;

import chess.domain.piece.position.Position;
import java.util.Objects;

public class King extends Piece {

    private static final String BLACK_DISPLAY = "♔";
    private static final String WHITE_DISPLAY = "♚";

    public King(Color color, Position position) {
        super(color, position);
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
        return color == king.color
                && position == king.position;

    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "King{" +
                "color=" + color +
                ", position=" + position +
                '}';
    }
}

