package chess;

import chess.contoller.WebChessController;

import static spark.Spark.*;

public class WebUIChessApplication {
	public static void main(String[] args) {
		staticFiles.location("/static");
		WebChessController webChessController = new WebChessController();

		get("/game", webChessController::startGame);

		post("/game", webChessController::runGame);

		post("/end", webChessController::endGame);
	}
}
