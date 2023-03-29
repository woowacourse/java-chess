package chess.domain.pieces;

public enum Team {

    BLACK("black"),
    WHITE("white"),
    EMPTY("empty");

    final String team;

    Team(String team) {
        this.team = team;
    }

    public boolean isBlackTeam() {
        return this == BLACK;
    }

    public boolean isWhiteTeam() {
        return this == WHITE;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public String getTeam() {
        return team;
    }
}
