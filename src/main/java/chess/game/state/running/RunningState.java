package chess.game.state.running;

import chess.domain.Team;
import chess.dto.SquareResponse;
import chess.game.state.GameState;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public abstract class RunningState implements GameState {
    protected static final String RUNNING_STATE_EXCEPTION_MESSAGE = "[ERROR] 잘못된 게임의 상태 입니다.(상태: 진행중)";

    protected RunningState() {
    }

    @Override
    public void startGame(Runnable runnable) {
        throw new IllegalStateException(RUNNING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public void movePiece(Runnable runnable) {
        runnable.run();
    }

    @Override
    public List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier) {
        return supplier.get();
    }

    @Override
    public double getTeamScore(DoubleSupplier doubleSupplier) {
        return doubleSupplier.getAsDouble();
    }

    @Override
    public Team getWinner() {
        throw new IllegalStateException(RUNNING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public void checkCheckmate(Runnable runnable) {
        runnable.run();
    }

    @Override
    public boolean hasWinner() {
        throw new IllegalStateException(RUNNING_STATE_EXCEPTION_MESSAGE);
    }
}
