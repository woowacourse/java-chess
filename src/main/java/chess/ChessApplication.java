package chess;

import chess.controller.ChessController;
import dao.DBChessGameDao;

public final class ChessApplication {
    public static void main(String[] args) {
        new ChessController(new DBChessGameDao()).run();
    }
}
