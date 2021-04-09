package chess.domain.chessgame;

import chess.domain.piece.Team;

public final class Turn {
    private Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public Turn() {
        this(Team.WHITE);
    }

    public Turn(String team) {
        this(Team.matchingTeam(team));
    }

    public Team now() {
        return team;
    }

    public void next() {
        team = team.oppositeTeam();
    }

    public void refresh() {
        team = Team.WHITE;
    }
}
