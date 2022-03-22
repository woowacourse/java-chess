package chess.domain;

public class King extends Piece {

    private static final String SYMBOL = "k";

    public King(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
