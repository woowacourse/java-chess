package chess.domain;

public enum TeamColor {
    WHITE("백팀"),
    BLACK("흑팀"),
    NONE("X"),
    ;

    private final String teamName;

    TeamColor(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
}
