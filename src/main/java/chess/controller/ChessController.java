package chess.controller;

import chess.domain.command.CommandConverter;
import java.util.NoSuchElementException;

import chess.domain.ChessScore;
import chess.domain.command.Command;
import chess.domain.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.displayGameRule();
        State state = State.create();
        while (!state.isFinished()) {
            state = processOneTurn(state);
        }
    }

    private State processOneTurn(State state) {
        try {
            checkTurn(state);
            Command command = CommandConverter.convertCommand(inputView.askCommand());
            state = state.proceed(command);
            outputView.displayChessBoard(state.getBoard());
            checkScore(state, command);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException exception) {
            outputView.displayErrorMessage(exception);
        }
        return state;
    }

    private void checkTurn(State state) {
        if (state.isRunning()) {
            outputView.displayTurn(state);
        }
    }

    private void checkScore(State state, Command command) {
        if (command.isStatus()) {
            ChessScore chessScore = state.generateScore();
            outputView.displayScore(chessScore);
        }
    }
}
