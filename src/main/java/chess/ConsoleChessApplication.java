package chess;

import chess.controller.ConsoleChessController;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ConsoleChessController consoleChessController = new ConsoleChessController();
        try {
            consoleChessController.run();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            consoleChessController.run();
        }
    }
}
