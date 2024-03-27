package chess.domain.piece.strategy;

import chess.domain.square.Square;

import java.util.List;

public class UnionStrategy implements MoveStrategy {

    private final List<MoveStrategy> moveStrategies;

    public UnionStrategy(final List<MoveStrategy> moveStrategies) {
        this.moveStrategies = moveStrategies;
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        for (final MoveStrategy moveStrategy : moveStrategies) {
            if (moveStrategy.canMove(source, target)) {
                return true;
            }
        }
        return false;
    }
}
