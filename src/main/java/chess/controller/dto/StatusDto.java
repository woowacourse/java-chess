package chess.controller.dto;

public final class StatusDto {

    private final String team;
    private final String score;

    public StatusDto(final String team, final String score) {
        this.team = team;
        this.score = score;
    }

    public String getTeam() {
        return team;
    }

    public String getScore() {
        return score;
    }
}
