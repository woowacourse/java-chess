package chess;

import chess.controller.Controller;

public class ChessGameApplication {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
