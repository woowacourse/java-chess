package chess.domain.RuleStrategy;

import chess.domain.position.Position;

public interface RuleStrategy {
	boolean canMove(Position sourcePosition, Position targetPosition);

	boolean canLeap();
}
