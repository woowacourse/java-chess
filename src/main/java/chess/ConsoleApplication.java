package chess;

import chess.controller.ConsoleChessGameController;

public class ConsoleApplication {

    public static void main(String[] args) {
        final ConsoleChessGameController consoleChessGameController = new ConsoleChessGameController();
        consoleChessGameController.run();
    }
}
