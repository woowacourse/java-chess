package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final double point;

    protected Piece(final Color color, final double point) {
        this.color = color;
        this.point = point;
    }

    public final Color getColor() {
        return color;
    }

    public double getPoint() {
        return point;
    }

    public abstract boolean isBlank();

    public abstract MoveStrategy getMoveStrategy();

    public abstract boolean isKing();

    public abstract boolean isPawn();

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
