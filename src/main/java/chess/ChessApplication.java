package chess;

import chess.controller.ChessController;
import chess.view.OutputView;

public class ChessApplication {
	public static void main(String[] args) {
		ChessController chessController = new ChessController();
		try {
			chessController.run();
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}
}
