package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public abstract class Piece {
    private final Side side;
    private final String initial;

    public Piece(Side side, String initial) {
        this.side = side;
        this.initial = initial;
    }

    public List<Position> route(Position from, Position to) {
        if (Objects.equals(from, to)) {
            throw new IllegalArgumentException();
        }

        int rowDifference = Position.differenceOfRow(from, to);
        int columnDifference = Position.differenceOfColumn(from, to);

        if (movable(rowDifference, columnDifference)) {
            return getRoute();
        }

        throw new IllegalArgumentException();
    }

    protected abstract List<Position> getRoute();

    protected abstract boolean movable(int rowDifference, int columnDifference);

    public boolean isSideEqualTo(Side side) {
        return this.side == side;
    }

    public String getInitial() {
        if (side == Side.WHITE) {
            return initial.toLowerCase(Locale.ROOT);
        }
        if (side == Side.BLACK) {
            return initial.toUpperCase(Locale.ROOT);
        }
        return initial;
    }
}
