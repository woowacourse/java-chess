package chess.domain.dto;

import chess.domain.board.Team;

public class ScoreDto {
    private Team team;
    private double score;

    public ScoreDto(Team team, double score) {
        this.team = team;
        this.score = score;
    }

    public Team getTeam() {
        return team;
    }

    public double getScore() {
        return score;
    }
}
