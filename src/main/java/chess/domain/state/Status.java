package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.result.ScoreResult;
import chess.domain.state.exception.UnsupportedCommandException;
import chess.domain.team.Team;

public class Status implements State {

    private final Board board;
    private final Team team;

    public Status(final Board board, final Team team) {
        this.board = board;
        this.team = team;
    }

    @Override
    public void receive(final String command) {
        throw new UnsupportedCommandException("점수확인 중에는 입력 받을 수 없습니다.");
    }

    @Override
    public State next() {
        return new Wait(board, team);
    }

    @Override
    public State before() {
        return new Wait(board, team);
    }

    @Override
    public ScoreResult bringResult() {
        return ScoreResult.yield(board.toList());
    }

    @Override
    public ResultType bringResultType() {
        return ResultType.SCORE;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean needsParam() {
        return false;
    }
}
