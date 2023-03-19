package chess.strategy;

import chess.domain.Position;

public class BishopStrategy implements MoveStrategy{

    private final MoveStrategy moveStrategy = new DiagonalStrategy();
    @Override
    public boolean isMovable(Position source, Position target) {
        return moveStrategy.isMovable(source, target);
    }
}
