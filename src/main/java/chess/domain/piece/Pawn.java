package chess.domain.piece;

import chess.domain.piece.position.Position;
import java.util.Objects;

public final class Pawn extends Piece {

    public static final String BLACK_PAWN_INIT_RANK = "7";
    public static final String WHITE_PAWN_INIT_RANK = "2";

    private static final String BLACK_DISPLAY = "♗";
    private static final String WHITE_DISPLAY = "♝";

    public Pawn(Color color, Position position) {
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
        Pawn pawn = (Pawn) o;
        return color == pawn.color
                && position == pawn.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "color=" + color +
                ", position=" + position +
                '}';
    }
}
