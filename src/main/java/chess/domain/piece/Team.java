package chess.domain.piece;

public enum Team {
    BLACK("흑"),
    WHITE("백");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public static Team turnOver(Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String getName() {
        return name;
    }
}
