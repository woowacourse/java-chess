package chess;

import chess.controller.FrontController;

public class Application {
    public static void main(String[] args) {
        FrontController frontController = new FrontController();
        frontController.init();
    }
}
