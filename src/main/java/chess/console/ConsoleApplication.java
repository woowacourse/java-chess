package chess.console;

import chess.console.controller.ChessController;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
