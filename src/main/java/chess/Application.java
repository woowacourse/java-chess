package chess;

import chess.controller.ChessGameController;

public class Application {

    public static void main(String[] args) {
        ChessGameController controller = new ChessGameController();

        controller.run();
    }
}
