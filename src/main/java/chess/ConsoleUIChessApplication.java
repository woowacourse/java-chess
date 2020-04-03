package chess;

import chess.contoller.ChessController;

public class ConsoleUIChessApplication {
	public static void main(String[] args) {
		ChessController chessController = new ChessController();
		chessController.run();
	}
}
