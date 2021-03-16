package chess.domain.piece;

public class Queen extends Piece {
    private static final String QUEEN_WORD = "K";

    public Queen(Color color) {
        super(color, QUEEN_WORD);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
