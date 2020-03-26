package chess.domain.RuleStrategy;

import java.util.Objects;

import chess.domain.position.Position;

public class KnightRuleStrategy implements RuleStrategy {

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
		return (chessFileGap + chessRankGap) == 3;
	}

	@Override
	public boolean canLeap() {
		return true;
	}

}
