package chess.game.state;

import chess.dto.SquareResponse;
import java.util.List;
import java.util.function.Supplier;

public class RunningState implements GameState {
    private static final String RUNNING_STATE_EXCEPTION_MESSAGE = "[ERROR] 잘못된 게임의 상태 입니다.(상태: 진행중)";
    public static final GameState STATE = new RunningState();

    private RunningState() {
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
}
