package chess;

import chess.controller.console.ConsoleChessController;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ConsoleChessController consoleChessController = new ConsoleChessController();
        consoleChessController.run();
    }
}
