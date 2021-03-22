package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.state.exception.UnsupportedCommandException;
import chess.domain.team.Team;
import java.util.HashMap;
import java.util.Map;

public class Status implements State<Map<Team, Double>> {

    private final Board board;
    private final Team team;

    public Status(Board board, Team team) {
        this.board = board;
        this.team = team;
    }

    @Override
    public void receive(String command) {
        throw new UnsupportedCommandException("점수확인 중에는 입력 받을 수 없습니다.");
    }

    @Override
    public State next() {
        return new Wait(board, team);
    }

    @Override
    public Map<Team, Double> result() {
        Map<Team, Double> result = new HashMap<>();
        result.put(Team.WHITE, board.score(Team.WHITE));
        result.put(Team.BLACK, board.score(Team.BLACK));
        return result;
    }

    @Override
    public ResultType resultType() {
        return ResultType.SCORE;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean needsParam() {
        return false;
    }
}
