package chess.domain.chesspiece;

public enum Team {
    BLACK("Black Team"),
    WHITE("White Team"),
    BLANK("");

    private String teamName;

    Team(String teamName) {
        this.teamName = teamName;
    }

    public static Team getOpponentTeam(Team team) {
        if (team == Team.BLACK) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }

    public String getTeamName() {
        return teamName;
    }
}
