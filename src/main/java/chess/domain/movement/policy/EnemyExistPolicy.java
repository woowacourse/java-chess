package chess.domain.movement.policy;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public class EnemyExistPolicy implements Policy {

    @Override
    public boolean isSatisfied(final Color color, final Position currentPosition, final boolean existEnemy) {
        return existEnemy;
    }
}
