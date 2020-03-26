package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.RuleStrategy.nonLeapableStrategy.NonLeapableStrategy;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class PawnRuleStrategy extends NonLeapableStrategy {

    protected final List<MoveDirection> catchableDirections = new ArrayList<>();

    public boolean canMoveToCatch(Position source, Position target) {
        return canCatchDirection(source, target) && canMoveRange(source, target);
    }

    private boolean canCatchDirection(Position source, Position target) {
        return catchableDirections.stream()
                .anyMatch(catchableDirections -> catchableDirections.isSameDirection(source, target));
    }

    @Override
    protected boolean canMoveRange(Position source, Position target) {
        int fileInterval = Math.abs(source.calculateFileIntervalTo(target));
        int rankInterval = Math.abs(source.calculateRankIntervalTo(target));

        return fileInterval == 0 && rankInterval == 1;
    }
}
