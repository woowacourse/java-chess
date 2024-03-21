package chess.domain.movement.policy;

import chess.domain.piece.Color;

public class EnemyExistPolicy implements Policy {

    @Override
    public boolean isSatisfied(final Color color, final boolean firstMove, final boolean existEnemy) {
        return existEnemy;
    }
}
