package chess;

import chess.controller.WebController;

public class WebApplication {
    public static void main(String[] args) {
        WebController webController = new WebController();
        webController.run();
    }
}
