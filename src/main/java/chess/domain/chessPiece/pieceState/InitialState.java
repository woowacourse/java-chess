package chess.domain.chessPiece.pieceState;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;

public class InitialState extends PieceState {

	public InitialState(RuleStrategy ruleStrategy) {
		super(ruleStrategy);
	}

	@Override
	public State shiftNextState(PieceColor pieceColor) {

		if (this.ruleStrategy instanceof PawnRuleStrategy) {
			return new MovedState(pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.MOVED_STATE_MOVABLE_RANGE));
		}
		return new MovedState(this.ruleStrategy);
	}

}
