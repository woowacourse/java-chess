package chess;

import chess.contoller.ChessWebController;

public class WebUIChessApplication {
	public static void main(String[] args) {
		ChessWebController chessWebController = new ChessWebController();
		chessWebController.run();
	}

}
