package chess.domain.RuleStrategy;

import chess.domain.position.Position;

public interface RuleStrategy {
    boolean canMove(Position source, Position target);

    boolean canLeap();
}
