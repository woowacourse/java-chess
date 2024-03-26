package chess.domain.piece;

import chess.domain.board.Position;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position source, Position target, Map<Position, Piece> board);

    public abstract boolean exists();

    public boolean doesNotExist() {
        return !exists();
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isNotColored(Color color) {
        return this.color != color;
    }

    public boolean hasOppositeColorFrom(Piece other) {
        return this.color == Color.oppose(other.color);
    }

    protected Color color() {
        return color;
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
