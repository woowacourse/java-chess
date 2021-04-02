package chess;

import chess.controller.WebUIChessController;

public class WebUIChessApplication {

    public static void main(String[] args) {
        WebUIChessController webController = new WebUIChessController();
        webController.run();
    }
}
