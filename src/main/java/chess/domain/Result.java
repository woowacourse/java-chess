package chess.domain;

import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.Score;
import java.util.Map;

public class Result {
    private final Map<TeamColor, Score> result;
    private final TeamColor winner;

    public Result(Map<TeamColor, Score> result, TeamColor winner) {
        this.result = result;
        this.winner = winner;
    }

    public Score whiteScore() {
        return result.get(TeamColor.WHITE);
    }

    public Score blackScore() {
        return result.get(TeamColor.BLACK);
    }

    public String getWinner() {
        return winner.name();
    }

}
