package chess.controller;

import chess.domain.ChessExecution;
import chess.state.Finished;
import chess.state.Ready;
import chess.state.State;
import chess.view.InputView;

public class ChessController {
    private State state;

    public ChessController() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();

        InputView inputView = InputView.getInstance();

        String[] command = inputView.scanCommand().split(" ");
        ChessExecution execution = ChessExecution.from(command[0]);

        while (!(state instanceof Finished)) {
            state = ChessExecution.from(input).run(state, input);
        }
    }
}
