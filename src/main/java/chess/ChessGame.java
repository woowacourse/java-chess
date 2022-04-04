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
import java.util.List;
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
            playGame();
            checkKingState();
        }
    }

    private void checkKingState() {
        if (!isRunning()) {
            return;
        }
        if (isKingChecked()) {
            OutputView.printKingCheckedMessage();
            return;
        }

        // 이동가능한 곳 중 checkmated가 등장했다면, 그곳으로 갈 수 없게 끔, 해야한다.
        final List<Position> kingCheckmatedPositions = state.getKingCheckmatedPositions();
        if (isAnyKingCheckmated(kingCheckmatedPositions)) {
            checkAllKingCheckMated(kingCheckmatedPositions);
        }
    }

    private boolean isAnyKingCheckmated(final List<Position> positions) {
        return positions.size() > 0;
    }

    private void checkAllKingCheckMated(final List<Position> positions) {
        if (isAllKingCheckmated(positions)) {
            OutputView.printALLKingCheckmatedMessage();
            gameSwitchOff();
            return;
        }
        OutputView.printKingCheckmatedMessage(positions);
    }

    private boolean isAllKingCheckmated(final List<Position> positions) {
        return state.isAllKingCheckmated(positions);
    }

    public boolean isKingChecked() {
        return state.isKingChecked();
    }

    private void playGame() {
        final String command = InputView.inputCommand();
        final GameCommand gameCommand = GameCommand.from(command);
        gameCommand.execute(command, this, printBoardInfoToState());
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
