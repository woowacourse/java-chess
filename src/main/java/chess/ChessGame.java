package chess;

import chess.domain.StatusScore;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.command.GameCommand;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public class ChessGame {

    private State state;
    public boolean isNotEnd = true;

    public ChessGame() {
        state = new Ready();
    }

    public void run() {
        OutputView.printStartMessage();

        while (isNotEnd) {
            final String command = InputView.inputCommand();
            final GameCommand gameCommand = GameCommand.from(command);
            gameCommand.execute(command, this);

            printBoardAndBeRunningIfStatus();
        }
    }

    public void start() {
        state = state.start();
    }

    public void move(final Positions movePositions) {
        state = state.move(movePositions);
    }

    public void status() {
        state = state.status();
    }

    public boolean isNotRunning() {
        return !state.isRunning();
    }

    public void turnOff() {
        isNotEnd = false;
    }

    public void end() {
        state = state.end();
    }

    private void printBoardAndBeRunningIfStatus() {
        if (isReadyOrRunning()) {
            OutputView.printBoard(getBoard());
        }
        if (isStatusInRunning()) {
            OutputView.printStatus(calculateStatus());

            beRunningIfStatus();
        }
        if (isFinishedAndGameEnd()) {
            OutputView.printFinalStatus(calculateStatus());
        }
    }

    private boolean isFinishedAndGameEnd() {
        return isNotEnd && state.isFinished();
    }

    private boolean isStatusInRunning() {
        return isNotEnd && state.isStatus();
    }

    private boolean isReadyOrRunning() {
        return isNotEnd && !state.isStatus();
    }

    public StatusScore calculateStatus() {
        return state.calculateStatus();
    }

    private void beRunningIfStatus() {
        if (state.isStatus()) {
            state = state.toRunningState();
        }
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }
}
