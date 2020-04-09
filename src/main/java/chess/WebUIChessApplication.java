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

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(8080);
		staticFiles.location("/static");

		DataSource dataSource = MySQLDataSource.getInstance();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		BoardDao boardDao = new BoardDaoImpl(jdbcTemplate);
		TurnDao turnDao = new TurnDaoImpl(jdbcTemplate);
		ChessService chessService = new ChessService(boardDao, turnDao);

		ChessGameController chessGameController = new ChessGameController(chessService);
		chessGameController.run();
	}
}