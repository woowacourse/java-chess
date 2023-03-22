package chess.game.state;

import chess.domain.Team;
import chess.dto.SquareResponse;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class WaitingState implements GameState {
    private static final String WAITING_STATE_EXCEPTION_MESSAGE = "[ERROR] 잘못된 게임의 상태 입니다.(상태: 대기중)";
    public static final GameState STATE = new WaitingState();

    private WaitingState() {
    }

    @Override
    public void startGame(Runnable runnable) {
        runnable.run();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public void movePiece(Runnable runnable) {
        throw new IllegalStateException(WAITING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier) {
        throw new IllegalStateException(WAITING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public double getTeamScore(DoubleSupplier doubleSupplier) {
        throw new IllegalStateException(WAITING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public Team getTurn() {
        throw new IllegalStateException(WAITING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public RunningState changeTurn() {
        throw new IllegalStateException(WAITING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public Team getWinner() {
        throw new IllegalStateException(WAITING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public void checkCheckmate(Runnable runnable) {
        throw new IllegalStateException(WAITING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean hasWinner() {
        throw new IllegalStateException(WAITING_STATE_EXCEPTION_MESSAGE);
    }
}
