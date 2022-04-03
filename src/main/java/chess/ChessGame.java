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

    public void start() {
        OutputView.printStartMessage();

        while (gameSwitch.isOn()) {
            final String command = InputView.inputCommand();
            final GameCommand gameCommand = GameCommand.from(command);
            gameCommand.execute(command, this, printBoardInfoToState());
        }
    }

    public void run() {
        state = state.run();
    }

    public void move(final Positions movePositions) {
        state = state.move(movePositions);
    }

    public void status() {
        state = state.status();
    }

    public StatusScore calculateStatus() {
        return state.calculateStatus();
    }

    public boolean isRunning() {
        if (gameSwitch.isOff()) {
            return false;
        }
        return state.isRunning();
    }

    public void gameSwitchOff() {
        gameSwitch = gameSwitch.off();
    }

    public void end() {
        state = state.end();
    }

    private Runnable printBoardInfoToState() {
        return () -> {
            if (isRunning()) {
                OutputView.printBoard(getBoard());
            }
            if (isStatusInRunning()) {
                OutputView.printStatus(calculateStatus());
            }
            if (isEndInRunning()) {
                OutputView.printFinalStatus(calculateStatus());
            }
            if (isEndInGameOff()) {
                OutputView.printEndMessage();
            }
        };
    }

    private boolean isStatusInRunning() {
        if (gameSwitch.isOff()) {
            return false;
        }
        return state.isStatus();
    }

    private boolean isEndInRunning() {
        if (gameSwitch.isOff()) {
            return false;
        }
        return state.isFinished();
    }

    private boolean isEndInGameOff() {
        return gameSwitch.isOff();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public void ready() {
        state = state.ready();
    }
}
