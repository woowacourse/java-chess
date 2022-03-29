package chess;

import chess.domain.Command;
import chess.state.State;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {

    private static final int COMMAND_INDEX = 0;

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
            final List<String> commands = InputView.requestCommands();
            final Command command = Command.of(commands.get(COMMAND_INDEX));

            state = command.run(state, commands);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }
}
