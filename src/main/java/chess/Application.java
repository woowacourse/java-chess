package chess;

import chess.controller.ChessGameController;
import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.db.ConnectGenerator;

public class Application {
    public static void main(String[] args) {
        ChessGameDao chessGameDao = new ChessGameDaoImpl(new ConnectGenerator().getConnection());
        ChessGameController chessGameController = new ChessGameController(chessGameDao);
        chessGameController.run();
    }
}
