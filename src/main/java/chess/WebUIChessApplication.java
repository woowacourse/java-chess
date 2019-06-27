package chess;

import chess.controller.ChessGameController;
import chess.view.OutputViewForWeb;

import java.util.*;

import static spark.Spark.*;

public class WebUIChessApplication {
	private final static ChessGameController chessGameController = new ChessGameController();

	public static void main(String[] args) {
		staticFiles.location("/static");
		get("/", (req, res) -> OutputViewForWeb.render(new HashMap<>(), "/index.html"));

		get("/new", (req, res) -> chessGameController.generateInitialChessGame());

		get("/select", (req, res) -> chessGameController.getRoomNumbers());

		post("/load", (req, res) -> chessGameController.loadProgressingChessGame(req));

		post("/move", (req, res) -> chessGameController.movePiece(req));

		post("/status", (req, res) -> chessGameController.getStatusScore(req));

		exception(Exception.class, (exception, req, res) -> chessGameController.handleException(res, exception));
	}
}
