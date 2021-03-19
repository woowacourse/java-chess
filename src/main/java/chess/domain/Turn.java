package chess.domain;

import chess.domain.piece.Team;

public class Turn {
    private Team team;

    public Turn() {
        team = Team.WHITE;
    }

    public Team now() {
        return team;
    }

    public void next() {
        team = team.oppositeTeam();
    }
}
