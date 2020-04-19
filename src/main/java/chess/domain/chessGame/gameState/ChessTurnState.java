package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public abstract class ChessTurnState implements GameState {

	@Override
	public GameState shiftEndState(final boolean isKingOnTargetPosition) {
		if (isKingOnTargetPosition) {
			return new KingCaughtState(getPieceColor());
		}
		return new EndState(getPieceColor());
	}

	@Override
	public boolean isEndState() {
		return false;
	}

	@Override
	public boolean isKingCaughtState() {
		return false;
	}

	@Override
	public abstract PieceColor getPieceColor();

}
