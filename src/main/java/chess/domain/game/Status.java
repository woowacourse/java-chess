package chess.domain.game;

import chess.domain.piece.attribute.Team;
import java.util.Map;

public final class Status {

    private final Map<Team, Double> TotalStatus;
    private final Team winner;

    public Status(Map<Team, Double> TotalStatus, Team team) {
        this.TotalStatus = TotalStatus;
        this.winner = team;
    }

    public Map<Team, Double> getTotalStatus() {
        return TotalStatus;
    }

    public Team getWinner() {
        return winner;
    }
}
