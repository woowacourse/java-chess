package chess.domain.piece;

public class King extends Piece {
    private static final String KING_WORD = "K";

    public King(Color color) {
        super(color, KING_WORD);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
