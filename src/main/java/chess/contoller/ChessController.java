package chess.contoller;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;

public class ChessController {
	public void run() {
		ChessBoard chessBoard = ChessBoardFactory.create();
	}
}
