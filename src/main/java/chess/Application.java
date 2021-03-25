package chess;

import chess.contoller.ChessController;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.play();
    }
}
