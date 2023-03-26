package chess;

import chess.dao.ChessGameDao;
import chess.dao.DBChessGameDao;
import chess.controller.ChessController;
import chess.domain.board.ChessBoard;

public class Application {
    public static void main(String[] args) {
        ChessGameDao chessGameDao = new DBChessGameDao();
        ChessController chessController = new ChessController(chessGameDao);
        chessController.runChessGame(ChessBoard.create());
    }
}
