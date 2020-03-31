package chess;

import chess.controller.ChessController;
import chess.controller.WebChessController;

public class WebUIChessApplication {
    public static void main(String[] args) {
        ChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
