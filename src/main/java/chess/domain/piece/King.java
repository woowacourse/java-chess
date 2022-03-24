package chess.domain.piece;

public final class King extends Piece {

    private static final String KING_NAME = "K";

    public King(Color color) {
        super(color, KING_NAME);
    }
}
