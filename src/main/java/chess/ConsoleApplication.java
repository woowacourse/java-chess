package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    private static final String START = "start";

    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();

        String command;
        do {
            outputView.initialPrint();
            command = inputView.scanCommand();
        } while (!command.equalsIgnoreCase(START));

        new ChessController().start();
    }
}
