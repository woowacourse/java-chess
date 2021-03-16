package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

import java.util.List;
import java.util.Locale;

public abstract class Piece {
    private final Side side;
    private final String initial;

    public Piece(Side side, String initial) {
        this.side = side;
        this.initial = initial;
    }

    public abstract List<Position> movable(Position from, Position to);

    public boolean isSideEqual(Side side) {
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
