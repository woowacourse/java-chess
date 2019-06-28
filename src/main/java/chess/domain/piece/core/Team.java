package chess.domain.piece.core;

public enum Team {
    BLACK("BLACK"),
    WHITE("WHITE");

    private String team;

    Team(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return team;
    }
}
