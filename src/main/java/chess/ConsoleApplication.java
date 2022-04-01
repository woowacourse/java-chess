package chess;

import chess.controller.InitialExecution;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();

        outputView.initialPrint();
        InitialExecution execution = InitialExecution.of(inputView.scanCommand());
        execution.run();
    }
}
