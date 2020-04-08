package chess;

import static spark.Spark.*;

import chess.controller.WebChessController;

public class WebUIChessApplication {

	public static void main(String[] args) {
		port(8080);
		staticFileLocation("/public");

		WebChessController webChessController = new WebChessController();

		webChessController.start();
		webChessController.run();
	}

}
