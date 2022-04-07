package chess.console;

import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.Camp;
import chess.domain.GameSwitch;
import chess.domain.StatusScore;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.command.GameCommand;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.domain.piece.Piece;
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

    private void playGame() {
        final String command = InputView.inputCommand();
        final GameCommand gameCommand = GameCommand.from(command);
        gameCommand.execute(command, this, printBoardInfoToState());
    }

    private void checkKingState() {
        if (!isRunning()) {
            return;
        }
        if (isKingChecked()) {
            OutputView.printKingCheckedMessage();
            return;
        }

        final List<Position> kingCheckmatedPositions = state.getKingCheckmatedPositions();
        if (isAnyKingCheckmated(kingCheckmatedPositions)) {
            isAllKingCheckMated(kingCheckmatedPositions);
        }
    }

    public boolean isAnyKingCheckmated(final List<Position> positions) {
        return positions.size() > 0;
    }

    public void isAllKingCheckMated(final List<Position> positions) {
        if (isAllKingCheckmated(positions)) {
            OutputView.printALLKingCheckmatedMessage();
            gameSwitchOff();

            return;
        }
        OutputView.printKingCheckmatedMessage(positions);
    }

    public boolean isAllKingCheckMated(final List<Position> positions,
                                       final Map<String, Object> model) {
        if (isAllKingCheckmated(positions)) {
//            OutputView.printALLKingCheckmatedMessage();
            System.err.println("킹이 체크 메이트를 빠져나올 구멍이 없데요");
//            model.put("isKingCheckmated", true);
//            model.put("message", "현재 턴의 킹 체크메이트 게임을 종료합니다.");
//            model.put("isRunning", false);
            gameSwitchOff();
            return true;
        }
        return false;
//        OutputView.printKingCheckmatedMessage(positions);
    }

    private boolean isAllKingCheckmated(final List<Position> positions) {
        return state.isAllKingCheckmated(positions);
    }

    public boolean isKingChecked() {
        return state.isKingChecked();
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

    public boolean isStatusInRunning() {
        if (isEndInGameOff()) {
            return false;
        }
        return state.isStatus();
    }

    public boolean isEndInRunning() {
        if (isEndInGameOff()) {
            return false;
        }
        return state.isFinished();
    }

    public boolean isEndInGameOff() {
        return gameSwitch.isOff();
    }

    public void ready() {
        state = state.ready();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public Camp getCamp() {
        return state.getCamp();
    }

    public List<Position> findKingCheckMatedPosition() {
        return state.getKingCheckmatedPositions();
    }
}
