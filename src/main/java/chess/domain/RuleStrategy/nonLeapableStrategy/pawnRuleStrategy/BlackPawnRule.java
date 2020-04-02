package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.position.MoveDirection;

import java.util.Arrays;

public class BlackPawnRule extends PawnRule {

    public BlackPawnRule() {
        super();
        this.movableDirections.add(MoveDirection.S);
        this.catchableDirections.addAll(Arrays.asList(MoveDirection.SW, MoveDirection.SE));
    }

}
