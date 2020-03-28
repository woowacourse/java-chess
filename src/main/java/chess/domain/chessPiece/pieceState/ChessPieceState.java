package chess.domain.chessPiece.pieceState;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public abstract class ChessPieceState implements PieceState {

	protected final RuleStrategy ruleStrategy;

	public ChessPieceState(RuleStrategy ruleStrategy) {
		this.ruleStrategy = ruleStrategy;
	}

	@Override
	public boolean canLeap() {
		return ruleStrategy.canLeap();
	}

	@Override
	public boolean canMove(Position sourcePosition, Position targetPosition) {
		return ruleStrategy.canMove(sourcePosition, targetPosition);
	}

	@Override
	public boolean canCatch(Position sourcePosition, Position targetPosition) {
		if (ruleStrategy instanceof PawnRuleStrategy) {
			return ((PawnRuleStrategy)ruleStrategy).canMoveToCatch(sourcePosition, targetPosition);
		}
		return ruleStrategy.canMove(sourcePosition, targetPosition);
	}

	@Override
	public abstract PieceState shiftNextState(PieceColor pieceColor);

}
