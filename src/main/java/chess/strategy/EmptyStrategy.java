package chess.strategy;

import chess.domain.board.Position;

public class EmptyStrategy implements MoveStrategy{
    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
