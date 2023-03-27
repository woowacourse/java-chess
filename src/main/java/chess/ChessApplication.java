package chess;

import chess.controller.ChessController;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController();

        chessController.run();
    }
}
