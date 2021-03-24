package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import java.util.List;
import java.util.Locale;

public abstract class Piece {

    private final Side side;
    private final String initial;
    private boolean initPosition = true;

    public Piece(Side side, String initial) {
        this.side = side;
        this.initial = initial;
    }

    public abstract List<Position> route(Position from, Position to);

    protected abstract boolean movable(int rowDifference, int columnDifference);

    protected abstract List<Position> getRoute(Position from, Position to);

    public abstract boolean isBlank();

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public abstract double score();

    public void moved() {
        initPosition = false;
    }

    protected boolean isInitPosition() {
        return initPosition;
    }

    public boolean isSideEqualTo(Side side) {
        return this.side == side;
    }

    public abstract boolean diagonal(Position from, Position to);

    public abstract boolean forward(Position from, Position to);

    public final String getInitial() {
        if (side == Side.WHITE) {
            return initial.toLowerCase(Locale.ROOT);
        }
        if (side == Side.BLACK) {
            return initial.toUpperCase(Locale.ROOT);
        }
        return initial;
    }
}
