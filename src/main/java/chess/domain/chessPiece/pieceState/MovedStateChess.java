package chess.domain.chessPiece.pieceState;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;

public class MovedStateChess extends ChessPieceState {

	public MovedStateChess(RuleStrategy ruleStrategy) {
		super(ruleStrategy);
	}

	@Override
	public PieceState shiftNextState(PieceColor pieceColor) {
		return this;
	}

}
