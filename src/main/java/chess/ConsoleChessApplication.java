package chess;

import chess.controller.ChessConsoleController;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        InputView consoleInputView = new InputView();
        OutputView consoleOutputView = new OutputView();
        ChessConsoleController chessConsoleController = new ChessConsoleController(consoleInputView, consoleOutputView);
        chessConsoleController.run();
    }
}
