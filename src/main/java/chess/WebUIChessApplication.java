package chess;

import chess.service.ChessService;
import chess.controller.WebController;
import chess.dao.ChessDao;
import chess.dao.InMemoryChessDao;
import chess.dao.MySqlChessDao;
import chess.database.MySqlConnector;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessDao chessDao = new InMemoryChessDao();
        if (MySqlConnector.getConnection() != null) {
            chessDao = new MySqlChessDao();
        }

        WebController controller = new WebController(new ChessService(chessDao));
        controller.play();
    }
}
