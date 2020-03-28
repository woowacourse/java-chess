package chess.domain.piece;

import chess.domain.player.Team;

public class PieceDto {

    private Team team;

    public PieceDto(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
