package chess.domain.chessgame;

import chess.domain.piece.Team;

public final class Turn {
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

    public void refresh() {
        team = Team.WHITE;
    }
}
