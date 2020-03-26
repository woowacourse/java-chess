package chess.domain.RuleStrategy.nonLeapableStrategy;

import java.util.Arrays;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public class BishopRuleStrategy extends NonLeapableStrategy {

	public BishopRuleStrategy() {
		this.movableDirections.addAll(
			Arrays.asList(MoveDirection.NE, MoveDirection.SE, MoveDirection.SW, MoveDirection.NW));
	}

	@Override
	protected boolean canMoveRange(Position source, Position target) {
		return true;
	}

}
