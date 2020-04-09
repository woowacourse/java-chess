package chess.dto;

public class TeamDTO {
    private final String teamName;

    public TeamDTO(final String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
}
