package chess;

import chess.domain.Command;
import chess.state.State;
import chess.view.InputView;

public class ChessGame {

    private State state;

    public ChessGame(final State state) {
        this.state = state;
    }

    public void run() {
        final String[] commands = InputView.requestCommands();
        final Command command = Command.of(commands[0]);

        state = command.run(state, commands);

        if (state.isNotEnded()) {
            run();
        }
    }
}
