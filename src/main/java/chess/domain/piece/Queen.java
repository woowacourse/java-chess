package chess.domain.piece;

public class Queen extends Piece {
    private static final String INITIAL_NAME = "Q";

    public Queen(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }
}
