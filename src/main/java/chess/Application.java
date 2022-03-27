package chess;

import chess.controller.cotroller.ChessController;
import chess.controller.cotroller.Init;

public final class Application {
    public static void main(String[] args) {
        ChessController controller = new Init();
        controller.start();
    }
}
