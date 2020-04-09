package chess;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.controller.ChessGameController;
import chess.database.DataSource;
import chess.database.JdbcTemplate;
import chess.database.MySQLDataSource;
import chess.database.dao.BoardDao;
import chess.database.dao.BoardDaoImpl;
import chess.database.dao.TurnDao;
import chess.database.dao.TurnDaoImpl;
import chess.service.ChessService;
import com.google.gson.Gson;

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(8080);
		staticFiles.location("/static");

		ChessService chessService = initializeChessService();
		Gson gson = new Gson();

		ChessGameController chessGameController = new ChessGameController(chessService, gson);
		chessGameController.run();
	}

	private static ChessService initializeChessService() {
		DataSource dataSource = MySQLDataSource.getInstance();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		BoardDao boardDao = new BoardDaoImpl(jdbcTemplate);
		TurnDao turnDao = new TurnDaoImpl(jdbcTemplate);
		return new ChessService(boardDao, turnDao);
	}
}