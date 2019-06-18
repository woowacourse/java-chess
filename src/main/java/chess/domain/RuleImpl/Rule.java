package chess.domain.RuleImpl;

import chess.domain.Position;

public interface Rule {
    boolean isValidMove(final Position origin, final Position target);
}
