package chess;

import chess.contoller.WebChessController;
import chess.contoller.WebErrorController;
import chess.dao.DataAccessException;

import static spark.Spark.*;

public class WebUIChessApplication {
	public static void main(String[] args) {
		staticFiles.location("/static");

		WebChessController webChessController = new WebChessController();
		WebErrorController webErrorController = new WebErrorController();

		get("/start_game", webChessController::startGame);

		get("/continue_game", webChessController::continueGame);

		get("/show", webChessController::showGame);

		post("/game", webChessController::runGame);

		post("/end", webChessController::endGame);

		exception(DataAccessException.class, webErrorController::renderStartError);

		exception(IllegalArgumentException.class, webErrorController::renderError);
	}
}
