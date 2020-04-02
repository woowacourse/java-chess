package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public class WhiteTurnState extends ChessTurnState {

	@Override
	public GameState shiftNextTurnState() {
		return new BlackTurnState();
	}

	@Override
	public PieceColor getPieceColor() {
		return PieceColor.WHITE;
	}

}