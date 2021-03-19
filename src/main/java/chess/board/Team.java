package chess.board;

public enum Team {
    WHITE("백"),
    BLACK("흑"),
    NONE("-");

    private final String teamName;

    Team(String teamName) {
        this.teamName = teamName;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public String teamName() {
        return teamName;
    }
}
