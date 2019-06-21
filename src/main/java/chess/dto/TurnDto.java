package chess.dto;

import chess.domain.Team;

public class TurnDto {
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }
}
