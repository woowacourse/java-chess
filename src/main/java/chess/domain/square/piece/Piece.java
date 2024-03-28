package chess.domain.square.piece;

import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import java.util.Map;
import java.util.Objects;

public abstract class Piece implements Square {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    protected abstract boolean canMove(PathFinder pathFinder);

    protected boolean isNotObstructed(PathFinder pathFinder, Map<Position, Square> board) {
        return pathFinder.find()
                .stream()
                .allMatch(position -> board.get(position) == Empty.getInstance());
    }

    @Override
    public final boolean isColor(Color color) {
        return this.color == color;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
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
        return Objects.hashCode(color);
    }

    public Color getColor() {
        return color;
    }
}
