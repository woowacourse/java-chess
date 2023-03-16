package chess;

import chess.controller.ChessController;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();

        chessController.run();
    }
}
