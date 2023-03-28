package chess;

import chess.controller.ChessController;
import chess.service.ChessService;
import dao.ChessStatusDao;
import dao.DBChessGameDao;

public final class ChessApplication {
    public static void main(String[] args) {
        final DBChessGameDao chessGameDao = new DBChessGameDao();
        final ChessStatusDao chessStatusDao = new ChessStatusDao();
        final ChessService chessService = new ChessService(chessGameDao, chessStatusDao);
        final ChessController chessController = new ChessController(chessService);
        chessController.run();
    }
}
