package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public abstract class ChessEndState implements GameState {

	protected final PieceColor pieceColor;

	protected ChessEndState(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}

	@Override
	public GameState shiftNextTurnState() {
		throw new UnsupportedOperationException("End 상태에서 변경될 수 없습니다.");
	}

	@Override
	public GameState shiftEndState(boolean isKingCaught) {
		throw new UnsupportedOperationException("End 상태에서 변경될 수 없습니다.");
	}

	@Override
	public PieceColor getTurnPieceColor() {
		return pieceColor;
	}

}
