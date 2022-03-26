package chess.domain;

import chess.domain.piece.Team;

public class TeamScore {

    private final Team team;
    private final double score;

    public TeamScore(Team team, double score) {
        this.team = team;
        this.score = score;
    }


    public String getTeam() {
        return team.getTeamName();
    }

    public double getScore() {
        return score;
    }
}
