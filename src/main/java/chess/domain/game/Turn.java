package chess.domain.game;

import chess.domain.piece.Team;

public class Turn {

    private Team team;

    public Turn() {
        this.team = Team.WHITE;
    }

    public void nextTurn() {
        team = team.oppositeTeam();
    }

    public boolean isRightTurn(final Team team) {
        return this.team == team;
    }
}
