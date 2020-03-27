package chess.domain.RuleStrategy;

import chess.domain.position.Position;

public interface RuleStrategy {

	boolean canLeap();

	boolean canMove(Position sourcePosition, Position targetPosition);

}
