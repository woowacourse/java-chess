package chess;

import chess.controller.Controller;
import chess.controller.ControllerFactory;

public class ChessGameApplication {
    public static void main(String[] args) {
        final Controller controller = ControllerFactory.mainController();
        controller.run();
    }
}
