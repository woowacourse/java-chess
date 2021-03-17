package chess.domain.piece;

public class Rook extends RealPiece {
    private static final String ROOK_WORD = "R";

    public Rook(Color color) {
        super(color, ROOK_WORD);
    }
}
