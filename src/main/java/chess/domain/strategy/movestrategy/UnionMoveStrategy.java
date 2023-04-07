package chess.domain.strategy.movestrategy;

import chess.domain.position.Position;

import java.util.List;

public final class UnionMoveStrategy implements MoveStrategy {

    private final List<MoveStrategy> moveStrategies;

    public UnionMoveStrategy(final List<MoveStrategy> moveStrategies) {
        this.moveStrategies = moveStrategies;
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return moveStrategies.stream()
                             .anyMatch(moveStrategy -> moveStrategy.isMovable(from, to));
    }
}
