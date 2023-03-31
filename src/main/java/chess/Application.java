package chess;

import chess.controller.ChessController;
import chess.repository.ChessGameDao;
import chess.repository.DbChessGameDao;
import chess.service.ChessService;

public final class Application {
    public static void main(String[] args) {
        ChessGameDao chessGameDao = new DbChessGameDao();
        ChessService chessService = new ChessService(chessGameDao);
        ChessController chessController = new ChessController(chessService);

        chessController.run();
    }
}
