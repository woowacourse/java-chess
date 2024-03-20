package chess.domain.movement;

import chess.domain.Color;
import chess.domain.movement.direction.Direction;
import chess.domain.movement.policy.Policy;

public class Movement {
    private final Policy policy;
    private final Direction direction;

    public Movement(final Policy policy, final Direction direction) {
        this.policy = policy;
        this.direction = direction;
    }

    public boolean isSatisfied(Color color, boolean firstMove, boolean existEnemy) {
        return policy.isSatisfied(color, firstMove, existEnemy);
    }
}
