package chess.controller;

import chess.controller.state.Ready;
import chess.controller.state.State;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private State state;

    public ChessController() {
        this.state = new Ready(new Game());
    }

    public void run() {
        OutputView.printGameStartMessage();

        while (state.isRunning()) {
            executeState();
        }
    }

    private void executeState() {
        try {
            final Command command = InputView.readCommand();
            state = executeCommand(command);
        } catch (final Exception e) {
            System.err.println(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    private State executeCommand(final Command command) {
        if (command.isStart()) {
            return state.start();
        }
        if (command.isMove()) {
            return state.move(command.getSource(), command.getTarget());
        }
        if (command.isStatus()) {
            return state.status();
        }
        return state.end();
    }
}
