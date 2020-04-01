package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public abstract class ChessTurnState implements GameState {

	protected final PieceColor pieceColor;

	protected ChessTurnState(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}

	@Override
	public GameState shiftEndState(boolean isKingOnTargetPosition) {
		if (isKingOnTargetPosition) {
			return new KingCaughtState(pieceColor);
		}
		return new EndState(pieceColor);
	}

	@Override
	public PieceColor getPieceColor() {
		return pieceColor;
	}

}
