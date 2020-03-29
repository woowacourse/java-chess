package chess.domain.chessPiece.pieceState;

import java.util.Objects;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public abstract class ChessMoveState implements PieceState {

	protected final RuleStrategy ruleStrategy;

	public ChessMoveState(RuleStrategy ruleStrategy) {
		Objects.requireNonNull(ruleStrategy, "체스 전략이 null입니다.");
		this.ruleStrategy = ruleStrategy;
	}

	@Override
	public boolean canLeap() {
		return ruleStrategy.canLeap();
	}

	@Override
	public boolean canMove(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);
		return ruleStrategy.canMove(sourcePosition, targetPosition);
	}

	@Override
	public boolean canCatch(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);

		if (ruleStrategy instanceof PawnRuleStrategy) {
			return ((PawnRuleStrategy)ruleStrategy).canMoveToCatch(sourcePosition, targetPosition);
		}
		return ruleStrategy.canMove(sourcePosition, targetPosition);
	}

	private void validate(Position sourcePosition, Position targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
	}

	@Override
	public abstract PieceState shiftNextState(PieceColor pieceColor);

}
