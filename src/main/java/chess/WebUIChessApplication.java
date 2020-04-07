package chess;

import chess.controller.WebChessController;

public class WebUIChessApplication {
	public static void main(String[] args) {
		WebChessController controller = new WebChessController();
		controller.run();
	}
}
