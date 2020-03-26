package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class BlackPawnRuleStrategy extends PawnRuleStrategy {

    private final List<MoveDirection> catchableDirections;

    public BlackPawnRuleStrategy() {
        this.movableDirections.add(MoveDirection.S);
        this.catchableDirections = Arrays.asList(
                MoveDirection.SW,
                MoveDirection.SE);
    }

    @Override
    protected boolean canMoveRange(Position source, Position target) {
        int fileInterval = Math.abs(source.calculateFileIntervalTo(target));
        int rankInterval = Math.abs(source.calculateRankIntervalTo(target));

        return fileInterval == 0 && rankInterval == 1;
    }
}
