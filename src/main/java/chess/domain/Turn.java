package chess.domain;

import chess.domain.piece.Team;

public class Turn {
    private static final String INVALID_TEAM = "올바르지 않은 팀입니다.";
    private final Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public Turn changeTurn() {
        if (this.team == Team.BLACK) {
            return new Turn(Team.WHITE);
        }
        if (this.team == Team.WHITE) {
            return new Turn(Team.BLACK);
        }
        throw new IllegalArgumentException(INVALID_TEAM);
    }

    public boolean isTurnOf(Team team) {
        return this.team == team;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return team.getName();
    }
}
