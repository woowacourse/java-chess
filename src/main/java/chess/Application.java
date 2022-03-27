package chess;

import chess.controller.commendlauncher.ChessController;
import chess.controller.commendlauncher.Init;

public final class Application {
    public static void main(String[] args) {
        ChessController controller = new Init();
        controller.start();
    }
}
