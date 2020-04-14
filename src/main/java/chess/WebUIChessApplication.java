package chess;

import static spark.Spark.*;

import chess.controller.WebChessController;
import chess.repository.GameDAO;
import chess.repository.JDBCGameDAO;
import chess.service.GameService;
import chess.utils.jdbc.DataSource;
import chess.utils.jdbc.JDBCTemplate;
import chess.utils.jdbc.MySQLDataSource;

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(8080);
		staticFiles.location("/templates");
		DataSource dataSource = new MySQLDataSource();
		GameDAO gameDAO = new JDBCGameDAO(new JDBCTemplate(dataSource));
		GameService gameService = new GameService(gameDAO);
		WebChessController controller = new WebChessController(gameService);
		controller.run();
	}
}
