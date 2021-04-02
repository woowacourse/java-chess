package chess;

import chess.controller.WebController;
import chess.db.MySQLConnector;
import chess.domain.dao.CommandDao;
import chess.domain.dao.CommandDatabase;
import chess.domain.dao.HistoryDao;
import chess.service.ChessService;

import java.sql.SQLException;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) throws SQLException {
        staticFiles.location("/public");

        final CommandDao commandDao = new CommandDao();
        final HistoryDao historyDao = new HistoryDao();
        final WebController webController = new WebController(new ChessService(commandDao, historyDao));
        webController.play();
    }
}
