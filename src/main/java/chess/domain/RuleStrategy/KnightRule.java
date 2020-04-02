package chess.domain.RuleStrategy;

import chess.domain.position.Position;

import java.util.Objects;

public class KnightRule implements Rule {

    public static final int KNIGHT_MOVABLE_RANGE = 3;

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        validate(sourcePosition, targetPosition);

        int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
        int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

        return isNotExistOnAxis(chessFileGap, chessRankGap) && isKnightCanMoveRange(chessFileGap, chessRankGap);
    }

    private void validate(Position sourcePosition, Position targetPosition) {
        Objects.requireNonNull(sourcePosition, "소스 위치가 존재하지 않습니다.");
        Objects.requireNonNull(targetPosition, "타겟 위치가 존재하지 않습니다.");
    }

    private boolean isNotExistOnAxis(int chessFileGap, int chessRankGap) {
        return chessFileGap != 0 && chessRankGap != 0;
    }

    private boolean isKnightCanMoveRange(int chessFileGap, int chessRankGap) {
        return (chessFileGap + chessRankGap) == KNIGHT_MOVABLE_RANGE;
    }

    @Override
    public boolean canLeap() {
        return true;
    }

}
