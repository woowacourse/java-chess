package chess;

import chess.controller.WebController;
import chess.db.MySQLConnector;
import chess.domain.dao.HistoryDao;
import chess.domain.dao.HistoryDatabase;
import chess.service.ChessService;

import java.sql.SQLException;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) throws SQLException {
        staticFiles.location("/public");

        final HistoryDao historyDao = new HistoryDao();
        if (MySQLConnector.getConnection() != null) {
             final WebController webController =
                     new WebController(new ChessService(historyDao), new HistoryDatabase(historyDao.selectAll()));
             webController.play();
        }

        final WebController webController = new WebController(new ChessService(historyDao));
        webController.play();
    }
}
