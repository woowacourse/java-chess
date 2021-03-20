package chess.controller;

import chess.controller.command.Command;
import chess.domain.Board;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.exception.ChessException;
import chess.exception.InvalidCommandException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void start() {
        OutputView.startGame();
        State state = new Ready(Board.getGamingBoard());
        Command command = command();
        if (command.isStart()) {
            startGame(state);
        }
    }

    private void startGame(State initialState) {
        State state = initialState.start();
        printBoard(state);

        while (!state.isFinished()) {
            state = turn(state);
        }
    }

    private void printBoard(State state) {
        OutputView.print(state.board(), state.side());

        if (state.isGameSet()) {
            OutputView.printWinner(state.winner());
        }
    }

    private State turn(State state) {
        try {
            state = execute(command(), state);
            printBoard(state);
        } catch (ChessException e) {
            OutputView.printError(e);
            return turn(state);
        }
        return state;
    }

    private State execute(Command command, State state) {

        if (command.isMove()) {
            state = state.move(command.source(), command.target());
        }

        if (command.isStatus()) {
            state = state.status();
            OutputView.print(state.score());
            return state;
        }

        if (command.isEnd()) {
            state = state.finished();
        }
        return state;
    }

    private Command command() {
        try {
            return Command.from(InputView.command());
        } catch (InvalidCommandException e) {
            OutputView.printError(e);
            return command();
        }
    }
}
