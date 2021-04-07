package chess.domain.piece;

public enum Team {
    BLACK("black"),
    WHITE("white");

    private final String teamString;

    Team(String teamString) {
        this.teamString = teamString;
    }

    public static Team convertStringToTeam(String team) {
        if ("black".equals(team)) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    public boolean isBlack() {
        return this.equals(Team.BLACK);
    }

    public boolean isWhite() {
        return this.equals(Team.WHITE);
    }

    public Team getOpposite() {
        if (this.equals(Team.BLACK)) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public String getTeamString() {
        return teamString;
    }
}
