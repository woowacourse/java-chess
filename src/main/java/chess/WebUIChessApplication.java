package chess;

import static spark.Spark.*;

import chess.controller.ChessController;
import chess.controller.WebChessController;
import chess.dao.BoardDAO;
import chess.dao.TurnInfoDAO;
import chess.service.ChessService;

public class WebUIChessApplication {
	public static void main(String[] args) {
		staticFiles.location("/public");

		ChessService service = new ChessService(new BoardDAO(), new TurnInfoDAO());
		ChessController controller = new WebChessController(service);

		controller.start();
		controller.playTurn();
	}
}
