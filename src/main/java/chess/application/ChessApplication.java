package chess.application;

import chess.controller.ChessController;
import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.service.ChessGameService;

public class ChessApplication {

    public static void main(String[] args) {
        final ChessGameDao chessGameDao = new ChessGameDaoImpl();
        final PieceDao pieceDao = new PieceDaoImpl();
        final ChessGameService chessGameService = new ChessGameService(chessGameDao, pieceDao);
        final ChessController chessController = new ChessController(chessGameService);
        chessController.run();
    }
}
