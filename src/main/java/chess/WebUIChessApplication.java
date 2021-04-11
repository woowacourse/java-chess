package chess;

import chess.controller.WebChessController;

public class WebUIChessApplication {
    public static void main(String[] args) {
        final WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
