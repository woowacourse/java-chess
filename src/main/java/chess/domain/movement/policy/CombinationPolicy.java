package chess.domain.movement.policy;

import chess.domain.piece.Color;
import java.util.List;

public class CombinationPolicy implements Policy {

    private final List<Policy> policies;

    public CombinationPolicy(final Policy... policies) {
        this.policies = List.of(policies);
    }

    @Override
    public boolean isSatisfied(final Color color, final boolean firstMove, final boolean existEnemy) {
        return policies.stream()
                .allMatch(policy -> policy.isSatisfied(color, firstMove, existEnemy));
    }
}
