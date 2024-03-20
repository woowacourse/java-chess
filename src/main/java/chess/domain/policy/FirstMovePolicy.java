package chess.domain.policy;

import chess.domain.Color;


public class FirstMovePolicy implements Policy {

    @Override
    public boolean isSatisfied(final Color color, final boolean firstMove, final boolean existEnemy) {
        return firstMove;
    }
}
