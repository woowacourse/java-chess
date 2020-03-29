package chess.domain.chessPiece.pieceState;

import java.util.Objects;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;

public class InitialState extends ChessMoveState {

	public InitialState(RuleStrategy ruleStrategy) {
		super(ruleStrategy);
	}

	@Override
	public PieceState shiftNextState(PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "피스 색상이 존재하지 않습니다.");

		if (this.ruleStrategy instanceof PawnRuleStrategy) {
			return new MovedState(pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.MOVED_STATE_MOVABLE_RANGE));
		}
		return new MovedState(this.ruleStrategy);
	}

}
