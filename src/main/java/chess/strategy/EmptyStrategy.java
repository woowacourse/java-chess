package chess.strategy;

import chess.domain.Position;

public class EmptyStrategy implements MoveStrategy{
    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
