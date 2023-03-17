package chess.domain.piece;

import chess.domain.move.Direction;

public abstract class Piece {

    private final boolean isWhite;

    protected Piece(final boolean isWhite) {
        this.isWhite = isWhite;
    }

    public abstract String name();

    public abstract boolean movable(Direction direction);

    public abstract boolean movableByCount(int count);

    protected boolean isWhite() {
        return isWhite;
    }
}
