package chess.console.domain.piece;

import chess.console.domain.board.Direction;
import chess.console.domain.board.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    protected final List<Direction> directions;

    public Piece(Color color, List<Direction> directions) {
        this.color = color;
        this.directions = directions;
    }

    public final boolean isSameType(Class<? extends Piece> type) {
        return this.getClass() == type;
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }

    public final boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public final String getColor() {
        return color.name().toUpperCase();
    }

    public final String getType() {
        return this.getClass().getSimpleName().toUpperCase();
    }

    public abstract boolean canMove(Position source, Position destination);

    public abstract Direction findDirection(Position source, Position destination);

    public abstract double getPoint();

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
