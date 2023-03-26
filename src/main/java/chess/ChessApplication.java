package chess;

import chess.controller.ChessController;
import chess.domain.dao.MysqlChessGameDao;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new MysqlChessGameDao());
        chessController.run();
    }
}
