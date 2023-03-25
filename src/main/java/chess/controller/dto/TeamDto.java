package chess.controller.dto;

public final class TeamDto {

    private final String team;

    public TeamDto(final String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
}
