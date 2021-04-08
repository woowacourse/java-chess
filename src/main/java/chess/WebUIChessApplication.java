package chess;

import chess.controller.WebChessController;

public class WebUIChessApplication {
    public static void main(String[] args) {
        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
