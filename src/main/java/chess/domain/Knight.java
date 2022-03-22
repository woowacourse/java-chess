package chess.domain;

public class Knight extends Piece {

    private static final String SYMBOL = "n";

    public Knight(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
