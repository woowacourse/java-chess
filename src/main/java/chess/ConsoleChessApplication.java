package chess;

import chess.controller.ConsoleChessController;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        ConsoleChessController chessController = new ConsoleChessController();
        chessController.run();
    }
}
