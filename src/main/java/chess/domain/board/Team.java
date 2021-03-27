package chess.domain.board;

public enum Team {
    WHITE("백"),
    BLACK("흑"),
    NONE("-");

    private final String teamName;

    Team(String teamName) {
        this.teamName = teamName;
    }

    public Team opposingTeam() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NONE;
    }

    public String teamName() {
        return teamName;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
