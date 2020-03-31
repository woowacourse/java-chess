package chess;

import chess.controller.ChessController;
import chess.controller.ConsoleChessController;
import chess.view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController consoleChessController = new ConsoleChessController();
        try {
            consoleChessController.run();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
