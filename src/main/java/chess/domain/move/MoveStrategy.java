package chess.domain.move;

import chess.domain.position.Position;

public abstract class MoveStrategy {

    protected boolean isSamePosition(Position source, Position target) {
        return source.equals(target);
    }

    public abstract boolean movable(Position source, Position target);
}
