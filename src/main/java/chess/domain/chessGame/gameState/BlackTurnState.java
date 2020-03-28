package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public class BlackTurnState extends ChessTurnState {

	public BlackTurnState() {
		super(PieceColor.BLACK);
	}

	@Override
	public GameState shiftNextTurnState() {
		return new WhiteTurnState();
	}

}
