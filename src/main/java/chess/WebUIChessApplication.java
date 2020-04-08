package chess;

import static spark.Spark.*;

import chess.controller.WebChessController;
import chess.domain.game.dao.MySQLGameDAO;
import chess.service.GameService;

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(8080);
		staticFiles.location("/templates");
		WebChessController controller = new WebChessController(new GameService(new MySQLGameDAO()));
		controller.run();
	}
}
