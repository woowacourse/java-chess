package chess.domain.piece.core;

public enum Team {
    BLACK("black"),
    WHITE("white");

    private String team;

    Team(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Team{" +
                "team='" + team + '\'' +
                '}';
    }
}
