package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public class BlackTurnState extends ChessTurnState {

	@Override
	public GameState shiftNextTurnState() {
		return new WhiteTurnState();
	}

	@Override
	public PieceColor getPieceColor() {
		return PieceColor.BLACK;
	}

}
