package chess.domain.piece;

public class Bishop extends RealPiece {
    private static final String BISHOP_WORD = "B";

    public Bishop(Color color) {
        super(color, BISHOP_WORD);
    }
}
