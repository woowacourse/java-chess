package chess;

import chess.controller.ChessController;

public class ConsoleApplication {
    public static void main(final String[] args) {
        final ChessController chessController = new ChessController();
        chessController.run();
    }
}
