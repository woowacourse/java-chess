package chess;

import chess.controller.ChessController;

public final class Application {
    public static void main(String[] args) {
        ChessController controller = new ChessController();
        controller.run();
    }
}
