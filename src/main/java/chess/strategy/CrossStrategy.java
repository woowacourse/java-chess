package chess.strategy;

import chess.domain.Position;

public class CrossStrategy implements MoveStrategy{
    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isSameXAs(target) || source.isSameYAs(target);
    }
}
