package chess;

import chess.controller.ChessController;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
