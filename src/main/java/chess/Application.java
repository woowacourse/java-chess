package chess;

import chess.controller.ChessController;
import chess.dao.ChessDao;

public class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(ChessDao.getInstance());
        chessController.run();
    }

}
