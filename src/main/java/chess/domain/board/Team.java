package chess.domain.board;

public enum Team {
    BLACK("black"),
    WHITE("white");

    private final String team;

    Team(String team) {
        this.team = team;
    }

    public static Team opposite(Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String team() {
        return team;
    }

    public boolean isBlack(Team team) {
        return team == BLACK;
    }

    @Override
    public String toString() {
        return team;
    }
}
