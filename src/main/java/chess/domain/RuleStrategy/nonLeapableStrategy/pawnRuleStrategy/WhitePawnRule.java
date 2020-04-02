package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.position.MoveDirection;

import java.util.Arrays;

public class WhitePawnRule extends PawnRule {

    public WhitePawnRule() {
        super();
        this.movableDirections.add(MoveDirection.N);
        this.catchableDirections.addAll(Arrays.asList(MoveDirection.NW, MoveDirection.NE));
    }

}
