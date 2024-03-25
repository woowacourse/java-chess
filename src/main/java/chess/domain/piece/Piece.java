package chess.domain.piece;

import chess.domain.board.Position;
import java.util.Objects;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position sourcePosition, Position targetPosition);

    public abstract boolean isPawn();

    public abstract boolean exists();

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean hasColorOf(Color color) {
        return this.color == color;
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
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
