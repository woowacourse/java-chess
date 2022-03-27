package chess.domain.game;

import chess.domain.piece.attribute.Team;
import java.util.Map;

public final class Status {

    private final Map<Team, Double> TotalStatus;
    private final Team winner;

    public Status(Map<Team, Double> TotalStatus) {
        this.TotalStatus = TotalStatus;
        this.winner = judgeWinner(TotalStatus);
    }

    private Team judgeWinner(Map<Team, Double> totalStatus) {
        if (totalStatus.get(Team.BLACK) < totalStatus.get(Team.WHITE)) {
            return Team.WHITE;
        }
        if (totalStatus.get(Team.BLACK) > totalStatus.get(Team.WHITE)) {
            return Team.BLACK;
        }
        return Team.NONE;
    }

    public Map<Team, Double> getTotalStatus() {
        return TotalStatus;
    }

    public Team getWinner() {
        return winner;
    }
}
