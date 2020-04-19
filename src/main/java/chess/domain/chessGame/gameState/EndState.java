package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public class EndState extends ChessEndState {

	EndState(final PieceColor pieceColor) {
		super(pieceColor);
	}

	@Override
	public boolean isKingCaughtState() {
		return false;
	}

}
