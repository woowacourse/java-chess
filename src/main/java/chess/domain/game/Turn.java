package chess.domain.game;

import chess.domain.piece.Team;
import java.util.Locale;

public class Turn {

    private Team team;

    public Turn() {
        team = Team.WHITE;
    }

    public Turn(final String team) {
        this.team = Team.valueOf(team.toUpperCase());
    }

    public Team now() {
        return team;
    }

    public void next() {
        team = team.oppositeTeam();
    }
}
