package chess.controller.dto;

public class TeamDto {
    private final String teamName;

    public TeamDto(final String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
}
