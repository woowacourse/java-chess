package chess.domain;

import chess.dto.TurnDto;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class Turn {
    private Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public static Turn init() {
        return new Turn(WHITE);
    }

    public static Turn load(String team) {
        return new Turn(Team.valueOf(team));
    }

    public Turn turnChanged() {
        if (team == BLACK) {
            return changeTurn(WHITE);
        }
        return changeTurn(BLACK);
    }

    private Turn changeTurn(Team team) {
        this.team = team;
        return this;
    }

    public boolean isTurn(Team team) {
        return this.team == team;
    }

    public Team getTeam() {
        return team;
    }

    public TurnDto toDto() {
        TurnDto turnDto = new TurnDto();
        turnDto.setTeam(team.name());

        return turnDto;
    }
}