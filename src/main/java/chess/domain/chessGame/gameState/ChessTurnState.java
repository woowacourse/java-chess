package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public abstract class ChessTurnState implements GameState {

	@Override
	public GameState shiftEndState(boolean isKingOnTargetPosition) {
		if (isKingOnTargetPosition) {
			return new KingCaughtState(getPieceColor());
		}
		return new EndState(getPieceColor());
	}

	@Override
	public abstract PieceColor getPieceColor();

}
