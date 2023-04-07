package chess.domain.strategy.movestrategy;

import chess.domain.position.Position;

import java.util.List;

public final class IntersectionMoveStrategy implements MoveStrategy {

    private final List<MoveStrategy> moveStrategies;

    public IntersectionMoveStrategy(final List<MoveStrategy> moveStrategies) {
        this.moveStrategies = moveStrategies;
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return moveStrategies.stream()
                             .allMatch(moveStrategy -> moveStrategy.isMovable(from, to));
    }
}
