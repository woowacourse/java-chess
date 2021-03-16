package chess.domain.piece;

public class Knight extends Piece {
    private static final String INITIAL_NAME = "N";

    public Knight(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }
}
