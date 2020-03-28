package chess.domain.chessPiece.pieceState;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;

public class InitialStateChess extends ChessPieceState {

	public InitialStateChess(RuleStrategy ruleStrategy) {
		super(ruleStrategy);
	}

	@Override
	public PieceState shiftNextState(PieceColor pieceColor) {
		if (this.ruleStrategy instanceof PawnRuleStrategy) {
			return new MovedStateChess(pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.MOVED_STATE_MOVABLE_RANGE));
		}
		return new MovedStateChess(this.ruleStrategy);
	}

}
