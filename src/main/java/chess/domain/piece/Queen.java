package chess.domain.piece;

public class Queen extends RealPiece {
    private static final String QUEEN_WORD = "Q";

    public Queen(Color color) {
        super(color, QUEEN_WORD);
    }
}
