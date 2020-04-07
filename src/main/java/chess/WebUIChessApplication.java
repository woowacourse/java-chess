package chess;

import chess.controller.WebChessController;

public class WebUIChessApplication {
    public static void main(String[] args) {
        WebChessController chessController = new WebChessController();

        chessController.run();
    }
}