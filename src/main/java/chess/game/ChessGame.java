package chess.game;

import chess.state.Start;
import chess.state.State;
import chess.state.Status;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessGame {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessGame(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printGameRule();
        State state = initState();
        state = playGame(state);
        managesResults(state);
    }

    private void managesResults(State state) {
        outputView.printStatusInstruction();
        state = proceed(state);
        if (state.isStatus()) {
            Status status = (Status) state;
            outputView.printScores(status.calculateScore());
        }
    }

    private State playGame(State state) {
        while (state.isRunning()) {
            outputView.printBoard(state.getBoard());
            state = proceed(state);
        }
        return state;
    }

    private State proceed(final State state) {
        try {
            return state.proceed(inputView.inputPlayerCommand());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return proceed(state);
        }
    }

    private State initState() {
        try {
            return Start.initState(inputView.inputPlayerCommand());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return initState();
        }
    }
}
