package chess.controller;

import chess.model.state.Ready;
import chess.model.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        State state = new Ready();
        outputView.printGameRule();
        while (!state.isFinished()) {
            state = proceed(state);
            printBoardIn(state);
        }
        printScoreIn(state);
    }

    private State proceed(final State state) {
        try {
            return state.proceed(inputView.inputCommand());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return proceed(state);
        }
    }

    private void printBoardIn(final State state) {
        if (!state.isFinished()) {
            outputView.printBoard(state.getBoard());
        }
    }

    private void printScoreIn(final State state) {
        if (state.isStatus()) {
            outputView.printGameResult(state.getScore(), state.getWinner());
        }
    }
}
