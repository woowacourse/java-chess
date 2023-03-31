package chess;

import chess.controller.ChessGameController;
import chess.database.ChessBoardDao;
import chess.database.ChessGameDao;

public class Application {
    public static void main(String[] args) {
        ChessGameController chessGameController = new ChessGameController(new ChessBoardDao(), new ChessGameDao());
        chessGameController.run();
    }
}
