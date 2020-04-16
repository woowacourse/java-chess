package chess;

import static spark.Spark.*;

import chess.controller.WebChessController;
import chess.dao.ChessGameDao;
import chess.dao.ChessHistoryDao;
import chess.dao.MySqlChessGameDao;
import chess.dao.MySqlChessHistoryDao;
import chess.service.ChessService;
import chess.web.repository.ConnectionManager;
import chess.web.repository.JdbcTemplate;
import chess.web.repository.MySqlConnectionManager;

public class WebUIChessApplication {

	public static void main(String[] args) {
		port(8080);
		staticFileLocation("/public");

		WebChessController webChessController = initWebController();
		webChessController.run();
	}

	private static WebChessController initWebController() {
		ConnectionManager connectionManager = MySqlConnectionManager.getInstance();
		JdbcTemplate template = new JdbcTemplate(connectionManager);

		ChessGameDao chessGameDao = new MySqlChessGameDao(template);
		ChessHistoryDao chessHistoryDao = new MySqlChessHistoryDao(template);
		ChessService chessService = new ChessService(chessGameDao, chessHistoryDao);

		return new WebChessController(chessService);
	}

}
