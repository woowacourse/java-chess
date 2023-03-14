package chess.domain;

public class Queen {

    private static final Queen blackQueen;
    private static final Queen whiteQueen;

    static {
        blackQueen = new Queen(Team.BLACK);
        whiteQueen = new Queen(Team.WHITE);
    }

    private final Team team;

    private Queen(final Team team) {
        this.team = team;
    }

    public static Queen of(final Team team) {
        if (team == Team.BLACK) {
            return blackQueen;
        }
        return whiteQueen;
    }
}
