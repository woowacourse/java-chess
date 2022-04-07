package chess;

import chess.controller.ConsoleChessController;

public class ConsoleApplication {

    public static void main(String[] args) {
        ConsoleChessController consoleChessController = new ConsoleChessController();
        consoleChessController.run();
    }

}
