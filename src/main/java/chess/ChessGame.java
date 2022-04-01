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
    public GameSwitch gameSwitch = GameSwitch.ON;

    public ChessGame() {
        state = new Ready();
    }

    public void run() {
        OutputView.printStartMessage();

        while (gameSwitch.isOn()) {
            final String command = InputView.inputCommand();
            final GameCommand gameCommand = GameCommand.from(command);
            gameCommand.execute(command, this, printBoardInfo());
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

    public void gameSwitchOff() {
        gameSwitch = gameSwitch.off();
    }

    public void end() {
        state = state.end();
    }

    private Runnable printBoardInfo() {
        return () -> {
            if (isReadyOrRunning()) {
                OutputView.printBoard(getBoard());
            }
            if (isStatusInRunning()) {
                OutputView.printStatus(calculateStatus());
            }
            if (isFinishedAndGameEnd()) {
                OutputView.printFinalStatus(calculateStatus());
            }
        };
    }

    private boolean isFinishedAndGameEnd() {
        return gameSwitch.isOn() && state.isFinished();
    }

    private boolean isStatusInRunning() {
        return gameSwitch.isOn() && state.isStatus();
    }

    private boolean isReadyOrRunning() {
        return gameSwitch.isOn() && !state.isStatus();
    }

    public StatusScore calculateStatus() {
        return state.calculateStatus();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public void toRunningState() {
        state = state.toRunningState();
    }
}
