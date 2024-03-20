package chess.domain.policy;

import chess.domain.Color;

public class EnemyExistPolicy implements Policy {

    @Override
    public boolean isSatisfied(final Color color, final boolean firstMove, final boolean existEnemy) {
        return existEnemy;
    }
}
