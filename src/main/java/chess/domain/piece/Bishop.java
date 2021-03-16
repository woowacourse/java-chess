package chess.domain.piece;

public class Bishop extends Piece {
    private static final String BISHOP_WORD = "B";

    public Bishop(Color color) {
        super(color, BISHOP_WORD);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
