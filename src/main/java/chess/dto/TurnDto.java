package chess.dto;

import chess.domain.player.Team;

public class TurnDto {

    private final String turn;

    public TurnDto(String turn) {
        this.turn = turn;
    }

    public TurnDto from(Team team) {
        return new TurnDto(team.getName());
    }

    public String getTurn() {
        return turn;
    }
}
