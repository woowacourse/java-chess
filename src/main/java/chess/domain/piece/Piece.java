package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;

public abstract class Piece {

    private final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public abstract String name();

    public abstract boolean movable(final Direction direction);

    public abstract boolean movableByCount(final int count);

    public boolean isSameTeam(final Color color) {
        return this.color.equals(color);
    }

    public Color team() {
        return color;
    }
}
