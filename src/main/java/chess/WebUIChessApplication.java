package chess;

import chess.contoller.WebChessController;

public class WebUIChessApplication {
    public static void main(String[] args) {
        WebChessController webChessController = new WebChessController();
        webChessController.init();
    }
}
