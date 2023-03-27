package chess;

import chess.controller.ChessGameController;
import chess.repository.ChessGameDao;
import chess.repository.ChessGameDaoImpl;
import chess.repository.ConnectGenerator;

public class Application {
    public static void main(String[] args) {
        ChessGameDao chessGameDao = new ChessGameDaoImpl(new ConnectGenerator().getConnection());
        ChessGameController chessGameController = new ChessGameController(chessGameDao);
        chessGameController.run();
    }
}
