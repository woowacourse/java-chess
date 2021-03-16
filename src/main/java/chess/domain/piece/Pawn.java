package chess.domain.piece;

public class Pawn extends Piece {
    private static final String PAWN_WORD = "P";

    public Pawn(Color color) {
        super(color, PAWN_WORD);
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
