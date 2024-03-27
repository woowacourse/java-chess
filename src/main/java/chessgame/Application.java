package chessgame;

import chessgame.controller.ChessController;

public class Application {
    public static void main(String[] args) {
        final var controller = new ChessController();

        controller.start();
    }
}
