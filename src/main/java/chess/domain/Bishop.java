package chess.domain;

public class Bishop extends Piece {

    private static final String SYMBOL = "b";

    public Bishop(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
