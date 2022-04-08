package chess.domain.game;

import chess.domain.piece.Team;

public class Turn {

    private Team team;

    public Turn(final Team team) {
        this.team = team;
    }

    public Turn() {
        this(Team.WHITE);
    }

    public void nextTurn() {
        team = team.oppositeTeam();
    }

    public boolean isRightTurn(final Team team) {
        return this.team == team;
    }

    public Team getTeam() {
        return team;
    }
}
