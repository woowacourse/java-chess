package chess.domain.movement.policy;

import chess.domain.Color;

public interface Policy {
    boolean isSatisfied(Color color, boolean firstMove, boolean existEnemy);
}
