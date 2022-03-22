package chess.domain;

public class Rook extends Piece {

    private static final String SYMBOL = "r";

    public Rook(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
