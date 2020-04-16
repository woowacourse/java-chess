package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public class KingCaughtState extends ChessEndState {

	KingCaughtState(final PieceColor pieceColor) {
		super(pieceColor);
	}

	@Override
	public boolean isKingCaughtState() {
		return true;
	}

}
