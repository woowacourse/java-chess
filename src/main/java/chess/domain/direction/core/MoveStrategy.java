package chess.domain.direction.core;

import java.util.function.Function;

import static chess.domain.direction.core.TargetStatus.EMPTY;
import static chess.domain.direction.core.TargetStatus.ENEMY;

public enum MoveStrategy {
    ONLY_EMPTY(targetStatus -> targetStatus.equals(EMPTY)),
    ONLY_ENEMY(targetStatus -> targetStatus.equals(ENEMY)),
    BOTH(targetStatus -> targetStatus.equals(EMPTY) || targetStatus.equals(ENEMY));

    Function<TargetStatus, Boolean> strategy;

    MoveStrategy(Function<TargetStatus, Boolean> strategy) {
        this.strategy = strategy;
    }

    public boolean canMove(TargetStatus targetStatus) {
        return strategy.apply(targetStatus);
    }
}
