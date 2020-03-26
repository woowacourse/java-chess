package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.position.MoveDirection;

import java.util.Arrays;
import java.util.List;

public class WhitePawnRuleStrategy extends PawnRuleStrategy {

    private final List<MoveDirection> catchableDirections;

    public WhitePawnRuleStrategy() {
        this.movableDirections.add(MoveDirection.N);
        this.catchableDirections = Arrays.asList(
                MoveDirection.NW,
                MoveDirection.NE);
    }
}
