package chess;

import chess.controller.ConsoleChessController;

import static chess.view.OutputView.printError;

public class ConsoleApplication {

    public static void main(String[] args) {
        try {
            new ConsoleChessController().run();
        } catch (IllegalArgumentException e) {
            printError(e.getMessage());
        }
    }
}
