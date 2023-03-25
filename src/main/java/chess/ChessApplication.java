package chess;

import chess.controller.ChessController;
import chess.dao.ChessGame.JdbcChessGameDao;
import chess.dao.Piece.JdbcPieceDao;
import chess.service.ChessGameService;

public class ChessApplication {

    public static void main(String[] args) {
        final JdbcChessGameDao chessGameDao = new JdbcChessGameDao();
        final JdbcPieceDao PieceDao = new JdbcPieceDao();
        final ChessGameService chessGameService = new ChessGameService(chessGameDao, PieceDao);
        final ChessController chessController = new ChessController(chessGameService);

        chessController.run();
    }
}
