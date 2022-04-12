package chess.console;

import chess.console.controller.ConsoleController;
import chess.console.view.InputView;
import chess.console.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        final ConsoleController consoleController = new ConsoleController(new OutputView(), new InputView());
        consoleController.start();
//        final ChessGame chessGame = new ChessGame();
//        chessGame.start();
    }
}
