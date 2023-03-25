package chess;

import chess.controller.ChessController;
import chess.controller.ErrorController;
import chess.dao.DBChessGameDao;

public final class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new ErrorController(), new DBChessGameDao());
        chessController.run();
    }
}
