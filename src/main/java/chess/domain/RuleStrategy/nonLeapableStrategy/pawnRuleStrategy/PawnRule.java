package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.RuleStrategy.nonLeapableStrategy.NonLeapable;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class PawnRule extends NonLeapable {

    protected PawnState pawnState;
    protected final List<MoveDirection> catchableDirections;

    public PawnRule() {
        this.pawnState = PawnState.initialState();
        this.catchableDirections = new ArrayList<>();
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
                .anyMatch(catchableDirections ->
                        catchableDirections.isSameDirectionFrom(sourcePosition, targetPosition));
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

        if (!pawnState.isPawnMovedState()) {
            pawnState = PawnState.switchedPawnMovedState();
            return (chessFileGap == 0) && (chessRankGap <= 2);
        }
        return (chessFileGap == 0) && (chessRankGap == 1);
    }
}
