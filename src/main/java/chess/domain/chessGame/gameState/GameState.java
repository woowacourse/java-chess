package chess.domain.chessGame.gameState;

import chess.domain.chessPiece.pieceType.PieceColor;

public interface GameState {

	GameState shiftNextTurnState();

	GameState shiftEndState(boolean isKingOnTargetPosition);

	PieceColor getPieceColor();

}
