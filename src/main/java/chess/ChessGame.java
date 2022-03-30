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
        State state = ready();

        while (!state.isGameOver()) {
            Command command = Command.from(InputView.command());
            progress(command, state);
        }

        OutputView.printResult(state);
        state.end();
    }

    private State ready() {
        InputView.announceStart();
        Board initBoard = new BoardInitializer().init();

        return new Ready(initBoard);
    }

    private void progress(Command command, State state) {
        if (command.isStart()) {
            state.start();
        }

        if (command.isMove()) {
            final List<Position> positions = command.makeSourceTargetPosition();
            state.changeTurn(positions);
        }

        if (command.isStatus()) {
            OutputView.printStatus(state.status());
            return;
        }

        if (command.isEnd()) {
            state.end();
        }

        Board currentBoard = state.board();
        ;
        OutputView.printBoard(currentBoard.cells());
    }
}
