package chess.domain.game;

public enum Team {
    BLACK("Black Team"),
    WHITE("White Team");

    private String teamName;

    Team(String teamName) {
        this.teamName = teamName;
    }

    public static Team getOpponentTeam(Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getTeamName() {
        return teamName;
    }
}
