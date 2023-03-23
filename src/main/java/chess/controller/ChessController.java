package chess.controller;

import chess.controller.state.Ready;
import chess.controller.state.State;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final InputView inputView;
    private final OutputView outputView;
    private Game game;
    private State state;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.game = new Game();
        this.state = new Ready();
    }

    public void run() {
        outputView.printGameStartMessage();

        while (state.isRunning()) {
            executeState();
        }
    }

    private void executeState() {
        try {
            final Command command = inputCommand();
            executeCommand(command);
        } catch (final IllegalStateException e) {
            System.err.println(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    private Command inputCommand() {
        try {
            return inputView.readCommand();
        } catch (final IllegalArgumentException e) {
            System.err.println(ERROR_MESSAGE_PREFIX + e.getMessage());
            return inputCommand();
        }
    }

    private void executeCommand(final Command command) {
        if (command.isStart()) {
            state = state.start();
            game = new Game();
            outputView.printChessBoard(game.getPieces());
        }
        if (command.isMove()) {
            state = state.next();
            play(command);
        }
        if (command.isEnd()) {
            state = state.end();
        }
    }

    private void play(final Command command) {
        try {
            game.move(command.getSource(), command.getTarget());
            outputView.printChessBoard(game.getPieces());
        } catch (final IllegalArgumentException e) {
            System.err.println(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }
}
