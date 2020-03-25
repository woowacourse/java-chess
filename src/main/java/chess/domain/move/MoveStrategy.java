package chess.domain.move;

import chess.domain.position.Position;

public abstract class MoveStrategy {

    protected abstract boolean movable(Position source, Position target);

    protected boolean isSamePosition(Position source, Position target) {
        return source.equals(target);
    }

}
