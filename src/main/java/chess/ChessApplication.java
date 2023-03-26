package chess;

import chess.controller.ChessController;
import chess.dao.ChessBoardDao;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new ChessBoardDao());
        chessController.run();
    }
}
