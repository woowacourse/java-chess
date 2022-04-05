package chess.domain.board;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    protected final String name;

    public Piece(Color color, String name) {
        this.color = color;
        this.name = name;
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

    public abstract boolean canMove(Position src, Position dest);

    public abstract Direction findDirection(Position src, Position dest);

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

    public String getName() {
        return color.name().toLowerCase() + name;
    }
}
