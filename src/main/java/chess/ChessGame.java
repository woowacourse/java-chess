package chess;

import chess.domain.Command;
import chess.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    private static final int MAIN_COMMAND_INDEX = 0;

    private State state;

    public ChessGame(final State state) {
        this.state = state;
    }

    public void run() {
        while (!state.isEnded()) {
            state = runCommand();
        }
    }

    private State runCommand() {
        try {
            final String[] commands = InputView.requestCommands();
            final Command command = Command.of(commands[MAIN_COMMAND_INDEX]);
            return command.run(state, commands);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return runCommand();
        }
    }
}
