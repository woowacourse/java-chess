package chess;

import chess.contoller.WebController;

public class WebApplication {
	public static void main(String[] args) {
		WebController webController = new WebController();
		webController.run();
	}
}
