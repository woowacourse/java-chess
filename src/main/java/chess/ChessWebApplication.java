package chess;

import chess.controller.ChessController;

public class ChessWebApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
