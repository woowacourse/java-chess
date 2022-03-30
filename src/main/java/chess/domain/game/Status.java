package chess.domain.game;

import chess.domain.piece.attribute.Team;
import java.util.Map;

public final class Status {

    private final Map<Team, Double> statusOfTeams;
    private final Team winner;

    public Status(Map<Team, Double> statusOfTeams, Team team) {
        this.statusOfTeams = statusOfTeams;
        this.winner = team;
    }

    public Map<Team, Double> getStatusOfTeams() {
        return statusOfTeams;
    }

    public Team getWinner() {
        return winner;
    }
}
