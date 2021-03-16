package chess.domain.piece;

public class King extends Piece {
    private static final String INITIAL_NAME = "K";

    public King(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }
}
