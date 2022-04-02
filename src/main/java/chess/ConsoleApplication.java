package chess;

import chess.controller.console.ChessController;

public class ConsoleApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
