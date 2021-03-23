package chess.domain.board;

public enum Team {
    BLACK("흑"),
    WHITE("백");

    private final String team;

    Team(String team) {
        this.team = team;
    }

    public static Team turnOver(Team team) {
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

    public boolean same(Team team) {
        return (this == team);
    }
}
