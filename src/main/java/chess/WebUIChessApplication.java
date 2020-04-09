package chess;

import chess.contoller.WebChessController;

import static spark.Spark.*;

public class WebUIChessApplication {
	public static void main(String[] args) {
		staticFiles.location("/static");

		WebChessController webChessController = new WebChessController();

		get("/start_game", webChessController::startGame);

		get("/continue_game", webChessController::continueGame);

		get("/show", webChessController::showGame);

		post("/game", webChessController::runGame);

		post("/end", webChessController::endGame);

		exception(RuntimeException.class, webChessController::renderStartError);

		exception(IllegalArgumentException.class, webChessController::renderError);
	}
}
