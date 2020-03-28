package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.RuleStrategy.nonLeapableStrategy.NonLeapableStrategy;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class PawnRuleStrategy extends NonLeapableStrategy {

    public static final int INITIAL_STATE_MOVABLE_RANGE = 2;
    public static final int MOVED_STATE_MOVABLE_RANGE = 1;

    protected final int movableRange;
    protected final List<MoveDirection> catchableDirections = new ArrayList<>();

    public PawnRuleStrategy(int movableRange) {
        this.movableRange = movableRange;
    }

    public boolean canMoveToCatch(Position sourcePosition, Position targetPosition) {
        validate(sourcePosition, targetPosition);
        return canCatchDirection(sourcePosition, targetPosition) && canCatchRange(sourcePosition, targetPosition);
    }

    private void validate(Position sourcePosition, Position targetPosition) {
        Objects.requireNonNull(sourcePosition, "소스 위치가 존재하지 않습니다.");
        Objects.requireNonNull(targetPosition, "타겟 위치가 존재하지 않습니다.");
    }

    private boolean canCatchDirection(Position sourcePosition, Position targetPosition) {
        return catchableDirections.stream()
                .anyMatch(catchableDirections -> catchableDirections.isSameDirectionFrom(sourcePosition, targetPosition));
    }

    private boolean canCatchRange(Position sourcePosition, Position targetPosition) {
        int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
        int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

        return (chessFileGap == 1) && (chessRankGap == 1);
    }

    @Override
    protected boolean canMoveRange(Position sourcePosition, Position targetPosition) {
        int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
        int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

        return (chessFileGap == 0) && (chessRankGap <= this.movableRange);
    }

}
