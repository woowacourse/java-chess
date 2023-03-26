package chess;

import chess.controller.ChessController;
import chess.dao.DbChessGameDao;

public final class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(new DbChessGameDao());
        chessController.run();
    }
}
