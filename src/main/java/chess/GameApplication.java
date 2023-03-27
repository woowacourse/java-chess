package chess;

import chess.controller.GameController;

public class GameApplication {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}
