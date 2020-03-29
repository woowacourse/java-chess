package chess.domain.chessPiece.pieceState;

import java.util.Objects;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;

public class MovedState extends ChessMoveState {

	public MovedState(RuleStrategy ruleStrategy) {
		super(ruleStrategy);
	}

	@Override
	public PieceState shiftNextState(PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "피스 색상이 존재하지 않습니다.");
		return this;
	}

}
