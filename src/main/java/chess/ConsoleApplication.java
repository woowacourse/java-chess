package chess;

import chess.controller.ConsoleGameController;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleGameController chessController = new ConsoleGameController();
        chessController.run();
    }
}
