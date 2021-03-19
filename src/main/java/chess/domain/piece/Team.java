package chess.domain.piece;

public enum Team {
    BLACK("black"),
    WHITE("white"),
    NOTHING("");

    private final String teamName;

    Team(final String teamName) {
        this.teamName = teamName;
    }

    public String teamName() {
        return this.teamName;
    }

    public String oppositeTeamName() {
        return this.oppositeTeam().teamName;
    }

    public Team oppositeTeam() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return this;
    }
}
