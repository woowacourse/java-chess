package chess;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.command.Command;
import chess.domain.command.StatusCommand;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessGame {

    public void run() {
        InputView.announceStart();
        State state = start();

        while (!state.isGameOver()) {
            Command command = Command.from(InputView.command());
            printStatus(command, state);
            state = progress(command, state);
            printBoard(command, state);
        }

        OutputView.printResult(state);
        state.end();
    }

    private State start() {
        Board initBoard = new BoardInitializer().init();
        return new Ready(initBoard);
    }

    private State progress(Command command, State state) {
        final State newState = command.changeChessState(state);
        return newState;
    }

    private void printBoard(final Command command, final State state) {
        if (!(command instanceof StatusCommand)) {
            Board currentBoard = state.board();
            OutputView.printBoard(currentBoard.cells());
        }
    }

    private void printStatus(final Command command, final State state) {
        if (command instanceof StatusCommand) {
            OutputView.printStatus(state.status());
        }
    }
}
