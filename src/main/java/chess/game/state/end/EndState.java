package chess.game.state.end;

import chess.domain.Team;
import chess.game.state.GameState;
import chess.game.state.running.RunningState;
import java.util.function.DoubleSupplier;

public abstract class EndState implements GameState {
    protected static final String END_STATE_EXCEPTION_MESSAGE = "[ERROR] 잘못된 게임의 상태 입니다.(상태: 종료됨)";
    private static final String INVALID_TEAM_EXCEPTION_MESSAGE = "[ERROR] 해당 팀에 대한 조건이 없습니다.";

    public static EndState createWinState(Team team) {
        if (team == Team.WHITE) {
            return WhiteWinState.STATE;
        }
        if (team == Team.BLACK) {
            return BlackWinState.STATE;
        }
        throw new IllegalArgumentException(INVALID_TEAM_EXCEPTION_MESSAGE);
    }

    protected EndState() {
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
    public double getTeamScore(DoubleSupplier doubleSupplier) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public Team getTurn() {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public RunningState changeTurn() {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isChecked() {
        return false;
    }
}
