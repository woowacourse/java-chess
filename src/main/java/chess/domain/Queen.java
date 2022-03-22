package chess.domain;

public class Queen extends Piece {

    private static final String SYMBOL = "q";

    public Queen(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
