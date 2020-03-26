package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import java.util.Arrays;

import chess.domain.position.MoveDirection;

public class BlackPawnRuleStrategy extends PawnRuleStrategy {

	public BlackPawnRuleStrategy() {
		this.movableDirections.add(MoveDirection.S);
		this.catchableDirections.addAll(Arrays.asList(MoveDirection.SW, MoveDirection.SE));
	}

}
