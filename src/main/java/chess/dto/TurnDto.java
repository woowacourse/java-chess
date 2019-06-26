package chess.dto;

public class TurnDto {
    private String team;

    public TurnDto() {
    }

    public TurnDto(final String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(final String team) {
        this.team = team;
    }
}
