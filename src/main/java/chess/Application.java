package chess;

import chess.controller.GameConnectionController;

public class Application {

    public static void main(String[] args) {
        GameConnectionController gameConnectionController = GameConnectionController.getInstance();
        gameConnectionController.run();
    }
}
