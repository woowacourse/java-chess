package chess.model.state.finished;

import chess.model.Team;
import chess.model.board.Board;
import chess.model.board.result.GameResult;
import java.util.Map;

public final class Status extends Finished {

    private final GameResult gameResult;

    public Status(Board board) {
        super(board);
        this.gameResult = new GameResult(board.getBoard());
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    @Override
    public Map<Team, Double> getScores() {
        return gameResult.getTeamScores();
    }

    @Override
    public Team getWinner() {
        return gameResult.pickWinnerTeam();
    }
}
