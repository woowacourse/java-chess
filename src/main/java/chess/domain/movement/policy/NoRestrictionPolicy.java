package chess.domain.movement.policy;

import chess.domain.piece.Color;

public class NoRestrictionPolicy implements Policy {

    @Override
    public boolean isSatisfied(final Color color, final boolean firstMove, final boolean existEnemy) {
        return true;
    }
}
