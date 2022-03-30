package chess.domain.game;

import chess.domain.piece.attribute.Team;
import java.util.Map;

public final class Status {

    private final Map<Team, Double> scoreOfTeams;
    private final Team winner;

    public Status(Map<Team, Double> scoreOfTeams, Team team) {
        this.scoreOfTeams = scoreOfTeams;
        this.winner = team;
    }

    public Map<Team, Double> getScoreOfTeams() {
        return scoreOfTeams;
    }

    public Team getWinner() {
        return winner;
    }
}
