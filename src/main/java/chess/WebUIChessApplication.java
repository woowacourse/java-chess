package chess;

import chess.controller.ChessWebController;
import chess.database.ChessDao;
import chess.database.InMemoryChessDao;
import chess.database.MySqlChessDao;
import chess.database.MySqlConnector;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessDao chessDao = new InMemoryChessDao();
        if (MySqlConnector.getConnection() != null) {
            chessDao = new MySqlChessDao();
        }
        ChessWebController controller = new ChessWebController(chessDao);
        controller.play();
    }
}
