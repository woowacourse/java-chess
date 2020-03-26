package chess.domain.RuleStrategy;

import java.util.Objects;

import chess.domain.position.Position;

public class KingRuleStrategy implements RuleStrategy {

	@Override
	public boolean canMove(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);
		int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return chessFileGap <= 1 && chessRankGap <= 1;
	}

	private void validate(Position sourcePosition, Position targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 존재하지 않습니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 존재하지 않습니다.");
	}

	@Override
	public boolean canLeap() {
		return true;
	}

}
