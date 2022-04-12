package chess.domain.board;

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

    @Override
    public String toString() {
        return "TeamScore{" +
                "team=" + team +
                ", score=" + score +
                '}';
    }
}
