package chess;

import chess.state.Start;
import chess.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printGameRule();

        State state = initState();
        while (state.isRunning()) {
            outputView.printBoard(state.getBoard());
            state = proceed(state);
        }
    }

    private State proceed(State state) {
        try {
            return state.proceed(inputView.inputPlayerCommand());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return proceed(state);
        }
    }

    private State initState() {
        try {
            return Start.initState(inputView.inputPlayerCommand());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return initState();
        }
    }
}
