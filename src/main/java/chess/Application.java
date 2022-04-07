package chess;

import chess.controller.ConsoleChessController;

public class Application {

    public static void main(String[] args) {
        ConsoleChessController chessController = new ConsoleChessController();
        chessController.play();
    }
}
