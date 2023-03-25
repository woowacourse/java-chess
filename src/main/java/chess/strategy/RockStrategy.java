package chess.strategy;

import chess.domain.board.Position;

public class RockStrategy implements MoveStrategy{

    private final MoveStrategy moveStrategy = new CrossStrategy();
    @Override
    public boolean isMovable(Position source, Position target) {
        return moveStrategy.isMovable(source, target);
    }
}
