package chess.domain.movement.policy;

import chess.domain.piece.Color;

public interface Policy {

    boolean isSatisfied(final Color color, final boolean firstMove, final boolean existEnemy);
}
