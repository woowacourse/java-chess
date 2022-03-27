package chess;

import chess.domain.Command;
import chess.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    private State state;

    public ChessGame(final State state) {
        this.state = state;
    }

    public void run() {
        OutputView.printStartMessage();
        do {
            play();
        } while (state.isNotEnded());
    }

    public void play() {
        try {
            final String[] commands = InputView.requestCommands();
            final Command command = Command.of(commands[0]);

            state = command.run(state, commands);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }
}
