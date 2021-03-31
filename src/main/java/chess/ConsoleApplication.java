package chess;

import chess.controller.console.ChessController;

public class ConsoleApplication {
    public static void main(String[] args) {
        final ChessController controller = new ChessController();
        controller.run();
    }
}