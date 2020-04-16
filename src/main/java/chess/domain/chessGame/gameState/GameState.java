package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public interface GameState {

	default GameState shiftNextTurnState() {
		throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
	}

	default GameState shiftEndState(final boolean isKingOnTargetPosition) {
		throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
	}

	boolean isEndState();

	boolean isKingCaughtState();

	PieceColor getPieceColor();

}
