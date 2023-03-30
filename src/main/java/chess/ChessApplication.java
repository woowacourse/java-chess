package chess;

import chess.controller.ChessController;
import chess.controller.action.TryCount;
import chess.domain.dao.LocalChessGameDao;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new LocalChessGameDao());
        chessController.run(new TryCount(10));
    }
}
