package chess;

import chess.controller.ChessGameController;
import chess.database.ChessBoardDao;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new ChessBoardDao());
        chessGameController.run();
    }
}
