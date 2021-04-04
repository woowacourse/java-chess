package chess.dto;

import chess.domain.Team;

public class PieceDTO {
    private final String team;
    private final String position;
    private final String initial;

    public PieceDTO(final Team team, final String position, final String initial) {
        this(String.valueOf(team), position, initial);
    }

    public PieceDTO(final String team, final String position, final String initial) {
        this.team = team;
        this.position = position;
        this.initial = initial;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    public String getInitial() {
        return initial;
    }
}
