package chess.game.state;

import chess.domain.Team;
import chess.dto.SquareResponse;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class EndState implements GameState {
    private static final String END_STATE_EXCEPTION_MESSAGE = "[ERROR] 잘못된 게임의 상태 입니다.(상태: 종료됨)";
    public static final GameState STATE = new EndState();

    private EndState() {
    }

    @Override
    public void startGame(Runnable runnable) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public void movePiece(Runnable runnable) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public double getTeamScore(DoubleSupplier doubleSupplier) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public Team getTurn(Supplier<Team> supplier) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }
}
