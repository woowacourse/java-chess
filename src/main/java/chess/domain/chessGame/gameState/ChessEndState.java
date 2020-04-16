package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public abstract class ChessEndState implements GameState {

	protected final PieceColor pieceColor;

	ChessEndState(final PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}

	@Override
	public boolean isEndState() {
		return true;
	}

	@Override
	abstract public boolean isKingCaughtState();

	@Override
	public PieceColor getPieceColor() {
		return pieceColor;
	}

}
