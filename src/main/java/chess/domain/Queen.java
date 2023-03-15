package chess.domain;

public class Queen extends Piece {

    private static final Queen blackQueen;
    private static final Queen whiteQueen;

    static {
        blackQueen = new Queen(Team.BLACK);
        whiteQueen = new Queen(Team.WHITE);
    }


    private Queen(final Team team) {
        super(team);
    }

    public static Queen of(final Team team) {
        if (team == Team.BLACK) {
            return blackQueen;
        }
        return whiteQueen;
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isDifferentTeam(piece) && (from.inLine(to) || from.inDiagonal(to));
    }
}
