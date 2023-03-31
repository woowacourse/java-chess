package chess;

import chess.controller.ChessController;
import chess.dao.ChessGameDao;
import chess.dao.MySqlChessGameDao;
import chess.database.DatabaseConnector;

public class Application {

    public static void main(String[] args) {
        ChessGameDao chessGameDao = new MySqlChessGameDao(new DatabaseConnector());
        ChessController chessController = new ChessController(chessGameDao);
        chessController.run();
    }
}
