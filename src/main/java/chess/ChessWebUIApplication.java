package chess;

import chess.controller.ChessWebController;

import static spark.Spark.staticFiles;

public class ChessWebUIApplication {
	public static void main(String[] args) {
		staticFiles.location("/public");

		ChessWebController chessWebController = new ChessWebController();

		chessWebController.run();
	}
}

