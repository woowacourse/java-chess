package chess.dto;

import chess.domain.Team;

public class TurnDto {
    private String team;

    public String getTeam() {
        return team;
    }

    public void setTeam(final String team) {
        this.team = team;
    }
}
