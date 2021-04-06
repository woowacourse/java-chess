package chess;

import chess.controller.WebController;

public class WebUIChessApplication {
    public static void main(String[] args) {
        WebController webController = new WebController();
        webController.run();
    }
}
