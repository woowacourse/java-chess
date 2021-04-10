package chess;

import chess.controller.console.ChessController;

public class Application {
    public static void main(String[] args) {
        final ChessController controller = new ChessController();
        controller.run();
    }
}
