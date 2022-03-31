package chess.domain.piece;

import static chess.domain.board.Direction.EAST;
import static chess.domain.board.Direction.NORTH;
import static chess.domain.board.Direction.NORTH_EAST;
import static chess.domain.board.Direction.NORTH_WEST;
import static chess.domain.board.Direction.SOUTH;
import static chess.domain.board.Direction.SOUTH_EAST;
import static chess.domain.board.Direction.SOUTH_WEST;
import static chess.domain.board.Direction.WEST;

import chess.domain.board.Direction;
import chess.domain.board.Position;
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
