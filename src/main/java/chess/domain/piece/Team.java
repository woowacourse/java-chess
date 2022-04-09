package chess.domain.piece;

public enum Team {
    WHITE("white"),
    BLACK("black");

    private final String team;

    Team(String team) {
        this.team = team;
    }

    public String getTeam() {
        return this.team;
    }
}
