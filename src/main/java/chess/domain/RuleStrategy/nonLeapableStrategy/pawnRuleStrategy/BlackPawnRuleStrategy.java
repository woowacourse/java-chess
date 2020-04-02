package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.position.MoveDirection;

import java.util.Arrays;

public class BlackPawnRuleStrategy extends PawnRuleStrategy {

    public BlackPawnRuleStrategy() {
        super();
        this.movableDirections.add(MoveDirection.S);
        this.catchableDirections.addAll(Arrays.asList(MoveDirection.SW, MoveDirection.SE));
    }

}
