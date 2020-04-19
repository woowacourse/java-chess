package chess;

import static spark.Spark.*;

import chess.controller.ChessController;

public class WebUIChessApplication {
	private static final ChessController chessController = new ChessController();

	public static void main(String[] args) {
		staticFileLocation("/templates");

		get("/", (req, res) -> chessController.start());

		get("/init", (req, res) -> chessController.init());

		post("/move", (req, res) -> {
			try {
				return chessController.move(req);
			} catch (IllegalArgumentException | UnsupportedOperationException e) {
				return e;
			}
		});

		get("/isEnd", (req, res) -> chessController.isEnd());

		get("/restart", (req, res) -> chessController.restart());

		get("/status", (req, res) -> chessController.status());

	}
}
