package chess.domain;

public class Pawn extends Piece {

    private static final String SYMBOL = "p";

    public Pawn(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
