package chess.domain;

public class King extends Piece {

    private static final King blackKing;
    private static final King whiteKing;

    static {
        blackKing = new King(Team.BLACK);
        whiteKing = new King(Team.WHITE);
    }

    private King(final Team team) {
        super(team);
    }

    public static King of(final Team team) {
        if (team == Team.BLACK) {
            return blackKing;
        }
        return whiteKing;
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        return false;
    }
}
