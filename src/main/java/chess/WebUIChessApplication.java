package chess;

import static spark.Spark.*;

import chess.controller.WebChessController;
import chess.repository.GameDAO;
import chess.repository.MySQLGameDAO;
import chess.service.GameService;
import chess.utils.jdbc.JDBCTemplate;

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(8080);
		staticFiles.location("/templates");
		GameDAO gameDAO = new MySQLGameDAO(new JDBCTemplate());
		GameService gameService = new GameService(gameDAO);
		WebChessController controller = new WebChessController(gameService);
		controller.run();
	}
}
