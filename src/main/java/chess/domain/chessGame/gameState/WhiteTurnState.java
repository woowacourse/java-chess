package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public class WhiteTurnState extends ChessTurnState {

	public WhiteTurnState() {
		super(PieceColor.WHITE);
	}

	@Override
	public GameState shiftNextTurnState() {
		return new BlackTurnState();
	}

}