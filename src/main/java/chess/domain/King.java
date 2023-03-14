package chess.domain;

public class King {

    private static final King blackKing;
    private static final King whiteKing;

    static {
        blackKing = new King(Team.BLACK);
        whiteKing = new King(Team.WHITE);
    }

    private final Team team;

    private King(final Team team) {
        this.team = team;
    }

    public static King of(final Team team) {
        if (team == Team.BLACK) {
            return blackKing;
        }
        return whiteKing;
    }
}
