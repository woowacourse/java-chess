package chess;

import chess.dao.ChessGameDao;
import chess.dao.MysqlChessGameDao;
import chess.controller.ChessController;
import chess.domain.board.ChessBoard;

public class Application {
    public static void main(String[] args) {
        ChessGameDao chessGameDao = new MysqlChessGameDao();
        ChessController chessController = new ChessController(chessGameDao);
        chessController.runChessGame(ChessBoard.create());
    }
}
