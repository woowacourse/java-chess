package chess;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.postion.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public final class ChessGame {

    public void run() {
        State state = start();

        while (!state.isGameOver()) {
            Command command = Command.from(InputView.command());
            state = progress(command, state);
            printBoard(command, state);
        }

        OutputView.printResult(state);
        state.end();
    }

    private State start() {
        InputView.announceStart();
        Board initBoard = new BoardInitializer().init();

        return new Ready(initBoard);
    }

    private State progress(Command command, State state) {
        if (command.isStart()) {
            return state.start();
        }

        if (command.isMove()) {
            final List<Position> positions = command.makeSourceTargetPosition();
            return state.changeTurn(positions);
        }

        if (command.isEnd()) {
            return state.end();
        }

        OutputView.printStatus(state.status());
        return state;
    }

    private void printBoard(final Command command, final State state) {
        if (!command.isStatus()) {
            Board currentBoard = state.board();
            OutputView.printBoard(currentBoard.cells());
        }
    }
}
