package chess;

import chess.command.GameCommand;
import chess.domain.StatusScore;
import chess.domain.board.Position;
import chess.domain.board.Positions;
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

        while (isNotEnd()) {
            final String command = InputView.inputCommand();
            final GameCommand gameCommand = GameCommand.from(command);
            gameCommand.execute(command, this);
            // flag로직이후로는 해당 플래그가 아닐 때가 기본으로 달아서 밑에 줘야한다.

            if (isReadyOrRunning()) {
                OutputView.printBoard(getBoard());
            }
            if (isStatusInRunnning()) {
                OutputView.printStatus(calculateStatus());
                //state 상태 -> (1) 명령어로 isStatus 진입확인 + (2) 그 상태에서의 board를 가지고  status 계산로직
                //running -> status상태에서만 출력됬으면 -> 다시 running으로 돌아가야한다?
                returnState();
            }
            // 플래그 업데이트 체크이후로는 로직없이 바로 조건문확인들어가야하는데
            // -> 업데이트된 플래그를 사용한 로직은 마지막에 추가할 수 있다.
            if (isFinishedAndGameEnd()) {
                OutputView.printFinalStatus(calculateStatus());
            }
        }
    }

    private boolean isFinishedAndGameEnd() {
        return isNotEnd() && isFinished();
    }

    private boolean isStatusInRunnning() {
        return isNotEnd() && isStatus();
    }

    private boolean isReadyOrRunning() {
        return isNotEnd() && !isStatus();
    }

    private void returnState() {
        state = state.returnState();
    }

    public boolean isNotEnd() {
        return isNotEnd;
    }

    public void start() {
        state = state.start();
    }

    public void move(final Positions movePositions) {
        state = state.move(movePositions);
    }

    public boolean isNotRunning() {
        return !state.isRunning();
    }

    private boolean isFinished() {
        return state.isFinished();
    }

    public void turnOff() {
        isNotEnd = false;
    }

    public void end() {
        state = state.end();
    }

    public StatusScore calculateStatus() {
        return state.calculateStatus();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }

    public void status() {
        state = state.status();
    }

    private boolean isStatus() {
        return state.isStatus();
    }
}
