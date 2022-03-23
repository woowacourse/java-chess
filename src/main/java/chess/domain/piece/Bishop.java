package chess.domain.piece;

import chess.domain.piece.position.Position;
import java.util.Objects;

public class Bishop extends Piece {

    private static final String BLACK_DISPLAY = "♙";
    private static final String WHITE_DISPLAY = "♟";

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position position) {
        // TODO: should be implemented
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
        Bishop bishop = (Bishop) o;
        return color == bishop.color
                && position == bishop.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Bishop{" +
                "color=" + color +
                ", position=" + position +
                '}';
    }
}
