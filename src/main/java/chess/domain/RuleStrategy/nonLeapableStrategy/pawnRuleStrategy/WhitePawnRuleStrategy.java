package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import java.util.Arrays;

import chess.domain.position.MoveDirection;

public class WhitePawnRuleStrategy extends PawnRuleStrategy {

	public WhitePawnRuleStrategy() {
		this.movableDirections.add(MoveDirection.N);
		this.catchableDirections.addAll(Arrays.asList(MoveDirection.NW, MoveDirection.NE));
	}

}
