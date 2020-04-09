package dto;

public class ScoreDTO {
    private String team;
    private double score;

    public ScoreDTO(String team, double score) {
        this.team = team;
        this.score = score;
    }

    public String getTeam() {
        return team;
    }

    public double getScore() {
        return score;
    }
}
