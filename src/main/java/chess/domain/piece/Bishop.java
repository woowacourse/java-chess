package chess.domain.piece;

public class Bishop extends Piece {
    private static final String INITIAL_NAME = "B";

    public Bishop(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }
}
