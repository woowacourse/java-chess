package chess.domain.piece;

public enum Team {
    BLACK("Black"), WHITE("White"), NONE("None");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isNone() {
        return this == NONE;
    }

    public String getTeamName() {
        return name;
    }
}
