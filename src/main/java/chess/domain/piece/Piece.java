package chess.domain.piece;

import chess.domain.move.Direction;

public abstract class Piece {

    private final String name;

    protected Piece(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public abstract boolean movable(Direction direction);

    public abstract boolean movableByCount(int count);
}
