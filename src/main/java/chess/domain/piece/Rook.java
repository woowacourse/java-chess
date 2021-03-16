package chess.domain.piece;

public class Rook extends Piece {
    private static final String INITIAL_NAME = "R";

    public Rook(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }
}
